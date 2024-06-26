#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.charge.chargerule;

import ${package}.domain.charge.ChargeRecord;
import ${package}.domain.charge.ChargeContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 为了应对套餐组合
 * 组合模式（Composite pattern)
 */
public class CompositeChargeRule {
    public List<ChargeRule> chargeRules;

    public List<ChargeRecord> doCharge(ChargeContext chargeContext){
        List<ChargeRecord> chargeRecords = new ArrayList<>();
        for(ChargeRule chargeRule : chargeRules){
            ChargeRecord chargeRecord = chargeRule.doCharge(chargeContext);
            if(chargeRecord != null){
                chargeRecord.setSessionId(chargeContext.getSession().getSessionId());
                //fill fields for persistence needs
                chargeRecord.setCreateTime(new Date());
                chargeRecord.setUpdateTime(new Date());
                chargeRecords.add(chargeRecord);
            }
        }
        return chargeRecords;
    }
}
