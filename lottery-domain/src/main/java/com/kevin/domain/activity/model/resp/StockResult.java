package com.kevin.domain.activity.model.resp;

import com.kevin.common.Result;

/**库存处理结果
 * @author wang
 * @create 2023-11-11-18:06
 */

public class StockResult extends Result {

    /**
     * 库存 Key
     */
    private String stockKey;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;

    public StockResult() {
    }

    public StockResult(String stockKey, Integer stockSurplusCount) {
        this.stockKey = stockKey;
        this.stockSurplusCount = stockSurplusCount;
    }

    public StockResult(Integer code, String info, String stockKey, Integer stockSurplusCount) {
        super(code, info);
        this.stockKey = stockKey;
        this.stockSurplusCount = stockSurplusCount;
    }

    public StockResult(Integer code, String info) {
        super(code, info);
    }

    public String getStockKey() {
        return stockKey;
    }

    public void setStockKey(String stockKey) {
        this.stockKey = stockKey;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }
}
