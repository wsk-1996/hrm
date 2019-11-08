package com.yueqian.query;

import com.yueqian.util.Myutil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryObject extends QueryObject{
    private String username;
    private String status;
    @Override
     protected void toparam(){
        nolimit();
         if(currentPage!=null&&pageSize!=null){
             addparam(" limit ?,? ",(currentPage-1)*pageSize,pageSize);
         }
     }

    @Override
    protected void nolimit() {
        if(Myutil.haslength(username)){
            addparam(" and username like ? ","%"+username+"%");
        }
        if(Myutil.haslength(status)&&!"-1".equals(status)){
            addparam(" and status = ? ",Integer.valueOf(status));
        }
    }
}
