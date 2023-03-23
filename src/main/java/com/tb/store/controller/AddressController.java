package com.tb.store.controller;

import com.tb.store.controller.ex.*;
import com.tb.store.entity.Address;
import com.tb.store.entity.User;
import com.tb.store.service.AddressService;
import com.tb.store.service.UserService;
import com.tb.store.util.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Resource
    private AddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address,HttpSession session){
        addressService.addNewAddress(getUidFromSession(session),getUsernameFromSession(session),address);
        return  new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        List<Address> addressByUid = addressService.getAddressByUid(getUidFromSession(session));
        return new JsonResult<>(OK,addressByUid);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.delAddress(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
