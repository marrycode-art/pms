package com.hui.customer.controller;

import com.hui.customer.bean.Customer;
import com.hui.customer.service.CustomerService;
import com.hui.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        List<Customer> list = customerService.getCustomerList();
        ModelAndView modelAndView = new ModelAndView("customer");
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        System.out.println(customer);
        customerService.saveInfo(customer);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/searchCusById/{id}", method = RequestMethod.GET)
    public ModelAndView searchCusById(@PathVariable(value = "id") Integer id){
        Customer customer = customerService.searchCusById(id);
        ModelAndView modelAndView = new ModelAndView("customer-edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping(value = "/searchInfo/{id}", method = RequestMethod.GET)
    public ModelAndView searchInfo(@PathVariable(value = "id") Integer id){
        Customer customer = customerService.searchCusById(id);
        ModelAndView modelAndView = new ModelAndView("customer-look");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateInfo(Customer customer){
        customerService.updateInfo(customer);
        return  "redirect:/customer/list";
    }

    @RequestMapping(value = "/batchDel", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDel(@RequestParam("ids[]") Integer[] ids){
        boolean isSuccess = customerService.batchDel(ids);
        Map<String,Object> map = new HashMap<String, Object>();
        if (isSuccess == true) {
            map.put("statusCode", 200);
            map.put("message", "删除成功!");
        }else {
            map.put("statusCode", 500);
            map.put("message", "删除失败!");
        }
        return map;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby){
        ModelAndView modelAndView = new ModelAndView("customer");
        List<Customer> list = customerService.search(cid, keyword, orderby);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/getComnameList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getComnameList(){
        return customerService.getComnameList();
    }

    @RequestMapping(value = "/getManagerInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getManagerInfo(@PathVariable(value = "id") Integer id){
        return customerService.getManagerInfo(id);
    }

    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile excel){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            //1.获取workbook对象
            Workbook workbook = WorkbookFactory.create(excel.getInputStream());
            //2.遍历表中所有的sheet表格
            for(int k = 0; k < workbook.getNumberOfSheets(); k++){
                Sheet sheet = workbook.getSheetAt(k);
                if (sheet == null){
                    break;
                }
                //3.遍历每一个sheet表格，得到数据
                for (int i = sheet.getFirstRowNum()+1; i < sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Customer customer = new Customer();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (row != null) {
                        //
                        String comperson = row.getCell(1).getStringCellValue();
                        customer.setCompanyperson(comperson);
                        //
                        String comname = row.getCell(2).getStringCellValue();
                        customer.setComname(comname);
                        //
                        Date date = row.getCell(3).getDateCellValue();
                        String format = simpleDateFormat.format(date);
                        Date addTime = simpleDateFormat.parse(format);
                        customer.setAddtime(addTime);
                        //
                        double numericCellValue = row.getCell(4).getNumericCellValue();
                        BigDecimal decimal = new BigDecimal(String.valueOf(numericCellValue));
                        customer.setComphone(decimal.toPlainString());

                        /*for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String value = ExcelUtils.parseValueToString(cell);
                            if (i > 0 && j == 0){
                                value = value.substring(0,value.indexOf("."));
                            }
                            System.err.print(value + "      ");
                        }
                        System.err.println();*/
                    }
                    customers.add(customer);
                }
            }
            customerService.batchInsert(customers);
            map.put("statusCode",200);
            map.put("message","上传成功");
        }catch (Exception exception) {
            exception.printStackTrace();
            map.put("statusCode",500);
            map.put("message","上传失败");
        }
        return map;
    }
}
