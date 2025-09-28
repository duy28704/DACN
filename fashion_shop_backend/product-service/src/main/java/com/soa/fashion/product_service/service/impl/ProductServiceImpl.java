package com.soa.fashion.product_service.service.impl;

import com.soa.fashion.product_service.dto.ProductDto;
import com.soa.fashion.product_service.entity.Product;
import com.soa.fashion.product_service.entity.ProductColor;
import com.soa.fashion.product_service.entity.ProductImg;
import com.soa.fashion.product_service.entity.ProductSize;
import com.soa.fashion.product_service.repository.ProductColorRepository;
import com.soa.fashion.product_service.repository.ProductImgRepository;
import com.soa.fashion.product_service.repository.ProductRepository;
import com.soa.fashion.product_service.repository.ProductSizeRepository;
import com.soa.fashion.product_service.service.ProductService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImgRepository productImgRepository;
    @Autowired
    private ProductColorRepository productColorRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Override
    @Transactional
    public boolean addFromExcel(MultipartFile file) {
        List<Product> products = new ArrayList<>();
        List<ProductImg> productImgs = new ArrayList<>();
        List<ProductColor> productColors = new ArrayList<>();
        List<ProductSize> productSizes = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            Set<String> bathId = new HashSet<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                String productId = formatter.formatCellValue(row.getCell(1)).trim();
                if (productId.isEmpty()) {
                    logger.info("Row {} bị bỏ qua vì mã sản phẩm trống", i);
                    continue;
                }

                if (productRepository.existsByProductId(productId)) {
                    logger.info("Sản phẩm trùng mã trong DB {}, bỏ qua", productId);
                    continue;
                }

                if (bathId.contains(productId)) {
                    logger.info("Sản phẩm trùng mã trong file {}, bỏ qua", productId);
                    continue;
                }
                bathId.add(productId);
                String title = formatter.formatCellValue(row.getCell(2)).trim();
                String priceStr = formatter.formatCellValue(row.getCell(3));
                double price = Double.parseDouble(priceStr);
                String description = formatter.formatCellValue(row.getCell(6)).trim();
                String material = formatter.formatCellValue(row.getCell(7)).trim();
                String usage = formatter.formatCellValue(row.getCell(8)).trim();
                String note = formatter.formatCellValue(row.getCell(9)).trim();
                String colorProduct = formatter.formatCellValue(row.getCell(10)).trim();
                String sizeProduct = formatter.formatCellValue(row.getCell(11)).trim();
                String category = formatter.formatCellValue(row.getCell(12)).trim();
                String images = formatter.formatCellValue(row.getCell(13)).trim();
                String[] urls = images.split(",");
                String[] colors = colorProduct.split(",");
                String[] sizes = sizeProduct.split(",");

                Product product = new Product();
                product.setProductId(productId);
                product.setTitle(title);
                product.setPrice(price);
                product.setDescription(description);
                product.setMaterial(material);
                product.setProductUsage(usage);
                product.setNote(note);
                product.setCategory(category);
                products.add(product);
                for (String url : urls) {
                    ProductImg productImg = new ProductImg();
                    productImg.setProduct(product);
                    productImg.setImageUrl(url.trim());
                    productImgs.add(productImg);
                }
                for (String color : colors) {
                    ProductColor productColor = new ProductColor();
                    productColor.setProduct(product);
                    productColor.setColor(color.trim());
                    productColors.add(productColor);
                }
                for (String size : sizes) {
                    ProductSize productSize = new ProductSize();
                    productSize.setProduct(product);
                    productSize.setSize(size.trim());
                    productSizes.add(productSize);
                }
            }
            productRepository.saveAll(products);
            productImgRepository.saveAll(productImgs);
            productColorRepository.saveAll(productColors);
            productSizeRepository.saveAll(productSizes);
            return true;
        } catch (Exception e) {
            logger.error("Lỗi khi import", e);
            return false;
        }
    }
    @Override
    public boolean addProduct(ProductDto productDto) {
        return false;
    }
}
