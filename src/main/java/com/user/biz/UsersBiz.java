package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.UsersMapper;
import com.user.vo.UsersVO;

@Service
public class UsersBiz implements Biz<String, UsersVO>{

	@Autowired
	UsersMapper udao;
	
	@Override
	public void register(UsersVO v) throws Exception {
		udao.insert(v);
	}

	@Override
	public void modify(UsersVO v) throws Exception {
		udao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		udao.delete(k);
	}

	@Override
	public UsersVO get(String k) throws Exception {
		return udao.select(k);
	}
	

	@Override
	public List<UsersVO> get() throws Exception {
		return udao.selectall();
	}
	
	public int getCustomer() throws Exception {
		return udao.selectcustomer();
	}
	
	public int getAdmin() throws Exception {
		return udao.selectadmin();
	}
	
	public List<UsersVO> getCustomerad() throws Exception {
		return udao.selectcustomerauthoritydetail();
	}
	
	public UsersVO clpget(String k) throws Exception {
		return udao.clpselect(k);
	}
	
	public String getselectchid(String k, String l) throws Exception {
		String id = udao.selectchid(k, l);
		if(id == null) {
			id = udao.selectchid(k, l);
		}
		if(id == null) {
			id = "0";
		}
		return id;
	}
	
	public String getselectchpwd(String k, String l, String m) throws Exception {
		String email = udao.selectchpwd(k, l, m);
		if(email == null) {
			email = udao.selectchpwd(k, l, m);
		}
		if(email == null) {
			email = "0";
		}
		return email;
	}
	
	public String getselectchpwdvalue(String k, String l, String m) throws Exception {
		String pwd = udao.selectchpwdvalue(k, l, m);
		if(pwd == null) {
			pwd = udao.selectchpwdvalue(k, l, m);
		}
		if(pwd == null) {
			pwd = "0";
		}
		return pwd;
	}


	
}
