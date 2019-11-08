package com.yueqian.query;

import com.yueqian.util.Myutil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentQueryObject extends QueryObject{
    private String title;
    @Override
    protected void toparam(){
        nolimit();
        if(currentPage!=null&&pageSize!=null){
            addparam(" limit ?,? ",(currentPage-1)*pageSize,pageSize);
        }
    }

    @Override
    protected void nolimit() {
        if(Myutil.haslength(title)){
            addparam(" and title like ?","%"+title+"%");
        }
    }
}
