package com.yueqian.query;

import com.yueqian.util.Myutil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeptQueryObject extends QueryObject{
    private String name;
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
    }
}
