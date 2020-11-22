package bg.sofia.uni.fmi.mjt.shopping.database;

import bg.sofia.uni.fmi.mjt.shopping.ProductCatalog;
import bg.sofia.uni.fmi.mjt.shopping.ProductInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataBase implements ProductCatalog {
    private final Map<String, ProductInfo> newMap;

    public DataBase() {
        this.newMap = new LinkedHashMap<>();
    }

    public void addInfo(String id, ProductInfo productInfo) {
        newMap.put(id, productInfo);
    }

    @Override
    public ProductInfo getProductInfo(String id) {
        return new ProductInfo(newMap.get(id).name(),
                newMap.get(id).description(), newMap.get(id).price());
    }
}
