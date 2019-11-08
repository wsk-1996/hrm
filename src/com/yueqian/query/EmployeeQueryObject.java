package com.yueqian.query;

import com.yueqian.util.Myutil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQueryObject extends QueryObject{
    private String jobId;
    private String name;
    private String cardId;
    private Integer sex;
    private String phone;
    private String deptId;
    @Override
     protected void toparam(){
        nolimit();
         if(currentPage!=null&&pageSize!=null){
             addparam(" limit ?,? ",(currentPage-1)*pageSize,pageSize);
         }
     }

    @Override
    protected void nolimit() {
        if(Myutil.haslength(name)){
            addparam(" and name like ? ","%"+name+"%");
        }
        if(Myutil.haslength(phone)){
            addparam(" and phone = ? ",phone);
        }
        if(Myutil.haslength(cardId)){
            addparam(" and card_id like ? ",cardId+"%");
        }
        if(Myutil.haslength(jobId)&&!"-1".equals(jobId)){
            addparam(" and job_id = ? ",jobId);
        }
        if(sex!=null&&sex!=-1){
            addparam(" and sex = ? ",sex);
        }
        if(Myutil.haslength(deptId)&&!"-1".equals(deptId)){
            addparam(" and dept_id = ? ",deptId);
        }
    }
}
