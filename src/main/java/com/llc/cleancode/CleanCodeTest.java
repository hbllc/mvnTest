package com.llc.cleancode;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author lilichuan
 */
public class CleanCodeTest {

    public void processData(List<String> data) {
        if (data != null && !data.isEmpty()) {
            for (String item : data) {
                if (item != null && !item.isEmpty()) {
                    if (item.startsWith("A")) {
                        // do something
                    } else if (item.startsWith("B")) {
                        // do something else
                    } else {
                        // do something else
                    }
                }
            }
        }
    }

    /**
     * 卫语句
     *
     * @param data 数据
     */
    public void processItems(List<String> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        data.forEach(item -> {
            try {
                this.doProcessData(item);
            } catch (Exception e) {

            }
        });
    }

    //校验数据
    private boolean checkItem(ItemData item) {
        if (item != null && item.getItemName() != null && !item.getItemName().isEmpty() && item.getItemNum() != null
                && item.getItemNum() > 0 && item.getCreateTime() != null) {
            if (item.getItemName() == "test" || item.getItemNum() > 5 || item.getCreateTime().isAfter(LocalDate.now())) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    //校验数据
    private boolean checkItem2(ItemData item) {
        checkNotNull(item);
        return checkItemLegal(item);
    }
    
    //校验业务数据
    private boolean checkItemLegal(ItemData item){
        if (item.getItemName() == "test") {
            return false;
        }

        if (item.getItemNum() > 5) {
            return false;
        }

        if (item.getCreateTime().isAfter(LocalDate.now())) {
            return false;
        }

//        if(!entryProcessingDate(item.getCreateTime())){
//            return false;
//        }
        return true;
        
    }

    private boolean entryProcessingDate(LocalDate createTime) {
        return LocalDate.now().isAfter(createTime);
    }

    //校验数据非空
    private boolean checkNotNull(ItemData item) {
        return item != null && item.getItemName() != null && !item.getItemName().isEmpty()
                && item.getItemNum() != null && item.getItemNum() > 0 && item.getCreateTime() != null;
    }






    /**
     * 优化后的代码
     *
     * @param data 数据
     */
    public void batchProcessDataB(List<String> data) {
        if (CollUtil.isEmpty(data)) {
            return;
        }

        data.forEach(item -> {
            try {
                this.doProcessData(item);
            } catch (Exception e) {

            }
        });
    }


    private void doProcessData(String item) {
        if (item == null || item.isEmpty() || item.isBlank()) {
            return;
        }
        if ("not".equals(item)) {
            return;
        }

        if(this.noNeedProcess(item)){
            return;
        }


        if (item.startsWith("A")) {
            // do something
        } else if (item.startsWith("B")) {
            // do something else
        } else {
            // do something else
        }
    }

    private boolean noNeedProcess(String item) {
        return "not".equals(item);
    }


    //使用策略模式进行优化
    private static Map<String, processStrategy> strategyMap;

    static {
        strategyMap.put("A", item -> {
            // do something
        });
        strategyMap.put("B", item -> {
            // do something else
        });

        strategyMap.put("DEFAULT", item -> {
            // do something else 记录日志，空实现
        });
    }

    private void doProcessDataByStrategy(String item) {
        if (item == null || item.isEmpty() || item.isBlank()) {
            return;
        }

        processStrategy processStrategy = Optional.ofNullable(strategyMap.get(item)).orElseGet(() -> strategyMap.get("DEFAULT"));
        processStrategy.process(item);

    }

    public static void main(String[] args) {
        CleanCodeTest cleanCodeTest = new CleanCodeTest();
        cleanCodeTest.doProcessDataByStrategy("A");
        List<String> a = List.of("A", "B", "C");
        cleanCodeTest.processData(a);
        cleanCodeTest.batchProcessDataB(a);

    }


}

//策略模式
interface processStrategy {
    void process(String item);
}

@Data
class ItemData {
    /**
     * 商品id
     */
    private String itemName;
    /**
     * 商品数量
     */
    private Integer itemNum;
    /**
     * 商品价格
     */
    private boolean isExist;
    /**
     * 商品价格
     */
    private Boolean closeFlag;
    /**
     * 商品创建时间
     */
    private LocalDate createTime;
}