package ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int curentIndex) {
        boolean ischeckId = true;
        do {
            System.out.println("Mời bạn nhập mã sách: ");
            this.productId = scanner.nextLine();
            boolean checkId = false;
            for (int i = 0; i < curentIndex; i++) {
                if (productId.equals(arrProduct[i].getProductId())) {
                    checkId = true;
                    System.out.println("Mã bị trùng, vui lòng nhập lại!");
                }
            }
            if (!checkId) {
                ischeckId = false;
            }
            if (!isValiBookId(this.productId)) {
                System.out.println("Mã sách phải có 4 ký tự và bắt đầu là B");
                this.productId = scanner.nextLine();
            }
        } while (ischeckId);
        boolean ischeckName = true;
        do {
            System.out.println("Mời bạn nhập tên sản phẩm (6-50 ký tự): ");
            this.productName = scanner.nextLine();
            boolean checkName = false;
            for (int i = 0; i < curentIndex; i++) {
                if (productName.equals(arrProduct[i].getProductName())) {
                    checkName = true;
                    System.out.println("Tên bị trùng vui lòng nhập lại!");
                }
            }
            if (!checkName) {
                ischeckName = false;
            }
            if (!isValiBookName(productName)) {
                System.out.println("Tên sản phẩm phải từ 6-50 ký tự, vui lòng nhập lại!");
                this.productName = scanner.nextLine();
            }
        } while (ischeckName);
        System.out.println("Mời nhập nhập: ");
        this.importPrice = Float.parseFloat(scanner.nextLine());
        if (this.importPrice <= 0) {
            System.out.println("Gía nhập phải lớn hơn 0, vui lòng nhập lại!");
            this.importPrice = Float.parseFloat(scanner.nextLine());
        }
        System.out.println("Mời nhập giá xuất: ");
        this.exportPrice = Float.parseFloat(scanner.nextLine());
        if (this.exportPrice < this.importPrice * 20 / 100) {
            System.out.println("Gía xuất phải có giá trị ít nhất 20%, vui lòng nhập lại!");
            this.exportPrice = Float.parseFloat(scanner.nextLine());
        }
        System.out.println("Nhập số lượng sản phẩm: ");
        this.quantity = Integer.parseInt(scanner.nextLine());
        if (this.quantity < 0) {
            System.out.println("Số lượng sản phẩm phải lớn hơn 0, vui lòng nhập lại!");
            this.quantity = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Nhập mô tả sản phẩm: ");
        this.descriptions = scanner.nextLine();
        System.out.println("Nhập trạng thái sản phẩm(true/false): ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
        if (this.status != true || this.status != false) {
            System.out.println("Trạng thái chỉ nhận giá trị true hoặc false");
            this.status = Boolean.parseBoolean(scanner.nextLine());
        }

    }

    private boolean isValiBookId(String productId) {
        return productId.length() == 4 && productId.startsWith("B");
    }

    private boolean isValiBookName(String productName) {
        return productName.length() >= 6 && productName.length() <= 50;
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s\n" +
                        "Gía nhập: %s - Gía xuất: %s - Lợi nhuận: %s\n" +
                        "Số lượng: %s - Mô tả: %s - Trạng thái: %s", this.productId, this.productName, this.importPrice, this.exportPrice,
                this.exportPrice, (this.profit = this.exportPrice - this.importPrice), this.quantity, this.descriptions, (this.status ? "Hoạt động" : "Không hoạt động"));
    }

    public void statisData(Scanner scanner, Product[] arrProduct, int currentProductlog) {
        System.out.println("Nhập vào khoảng giá đầu: ");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào khoảng giá cuối: ");
        float toPrice = Float.parseFloat(scanner.nextLine());
        for (Product product : arrProduct) {
            if (product.getExportPrice() >= fromPrice && product.getExportPrice() <= toPrice) {
                currentProductlog++;
                break;
            }
        }
        System.out.println("Thống kê sản phẩm trong khoảng giá: ");
        for (int i = 0; i < arrProduct.length - 1; i++) {
            System.out.println(String.format("Từ %.2f đến %.2f: %d sản phẩm", fromPrice, toPrice, currentProductlog));
        }
    }

}
