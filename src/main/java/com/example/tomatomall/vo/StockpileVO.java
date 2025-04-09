package com.example.tomatomall.vo;

import com.example.tomatomall.po.Stockpile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockpileVO {
    private Integer id;
    private Integer productId;
    private Integer amount;
    private Integer frozen;

    /**
     * 计算可用库存（可售数量）
     * @return 可用库存量
     */
    public Integer getAvailable() {
        return amount - frozen;
    }

    /**
     * 转换为PO对象
     * @return Stockpile
     */
    public Stockpile toPO() {
        Stockpile po = new Stockpile();
        po.setId(this.id);
        po.setProductId(this.productId);
        po.setAmount(this.amount);
        po.setFrozen(this.frozen);
        return po;
    }
}
