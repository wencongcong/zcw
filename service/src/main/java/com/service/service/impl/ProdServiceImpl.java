package com.service.service.impl;

import com.service.entity.Prod;
import com.service.enums.ErrorEnum;
import com.service.mapper.ProdMapper;
import com.service.result.Result;
import com.service.service.ProdService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("prodService")
public class ProdServiceImpl implements ProdService {

    @Resource
    private ProdMapper prodMapper;

    @Override
    public List<Prod> queryAll(Map map) {
        return prodMapper.queryAll(map);
    }

    @Override
    public int insetOneProd(Prod prod) {
        return prodMapper.insetOneProd(prod);
    }

    @Override
    public Result queryAllinsert(MultipartFile file) throws Exception{
        int result = 0;
//		存放excel表中所有user细腻
        List<Prod> prodList = new ArrayList<>();
        /**
         *
         * 判断文件版本
         */
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        InputStream ins = file.getInputStream();

        Workbook wb = null;

        if(suffix.equals("xlsx")){
            wb = new XSSFWorkbook(ins);

        }else{
            wb = new HSSFWorkbook(ins);
        }
        /**
         * 获取excel表单
         */
        Sheet sheet = wb.getSheetAt(0);
        /**
         * line = 1 :从表的第二行开始获取记录
         *
         */
        if(null != sheet){

            for(int line = 1; line <= sheet.getLastRowNum();line++){

                Prod prod = new Prod();

                Row row = sheet.getRow(line);

                if(null == row){
                    continue;
                }
                /**
                 * 判断单元格类型是否为文本类型
                 */
//                if(1 != row.getCell(0).getCellType()){
//                    throw new Exception("单元格类型不是文本类型！");
//                }

                /**
                 * 获取第一个单元格的内容
                 */
                row.getCell(0).setCellType(CellType.STRING);
                String promoney = row.getCell(0).getStringCellValue();
                /**
                 * 获取第二个单元格的内容
                 */
                row.getCell(1).setCellType(CellType.STRING);
                String productname = row.getCell(1).getStringCellValue();
                /**
                 * 获取其他单元格信息
                 * */
                row.getCell(2).setCellType(CellType.STRING);
                String procount = row.getCell(2).getStringCellValue();
                row.getCell(3).setCellType(CellType.STRING);
                String otime = row.getCell(3).getStringCellValue();
                row.getCell(4).setCellType(CellType.STRING);
                String ptime = row.getCell(4).getStringCellValue();
                row.getCell(5).setCellType(CellType.STRING);
                String rate = row.getCell(5).getStringCellValue();
                row.getCell(6).setCellType(CellType.STRING);
                String integral = row.getCell(6).getStringCellValue();
                row.getCell(7).setCellType(CellType.STRING);
                String deduct = row.getCell(7).getStringCellValue();
                row.getCell(8).setCellType(CellType.STRING);
                String slname = row.getCell(8).getStringCellValue();
                row.getCell(9).setCellType(CellType.STRING);
                String depaname = row.getCell(9).getStringCellValue();
                row.getCell(10).setCellType(CellType.STRING);
                String subsidy=row.getCell(10).getStringCellValue();

                prod.setProMoney(promoney);
                prod.setProductsName(productname.trim());
                prod.setProCount(procount.trim());
                prod.setOtime(otime);
                prod.setPtime(ptime);
                prod.setRate(rate.trim());
                prod.setIntegral(integral.trim());
                prod.setDeduct(deduct.trim());
                prod.setSlname(slname.trim());
                prod.setDepaname(depaname.trim());
                prod.setEnable(0);
                prod.setSubsidy(subsidy.trim());
                prodList.add(prod);
            }

            for(Prod prodInfo:prodList){
                /**
                 * 判断数据库表中是否存在用户记录，若存在，则更新，不存在，则保存记录
                 */
                if (prodMapper.chaName(prodInfo.getProductsName(),prodInfo.getDepaname())>1){
                    result = 2;
                    prodMapper.updateOneProd(prodInfo);
                }else {
                    result = 1;
                    prodMapper.insetOneProd(prodInfo);
                }
            }
        }

        if (result==1){
            return Result.success(result, "插入成功");
        }else {
            return Result.success(result, "已有产品,更新成功");
        }
    }

    @Override
    public int chaName(String accept,String depaname) {
        return prodMapper.chaName(accept,depaname);
    }

    @Override
    public int updateOneProd(Prod prod) {
        return prodMapper.updateOneProd(prod);
    }

    @Override
    public int delteOneProd(int id, String productsName) {
        return prodMapper.delteOneProd(id, productsName);
    }

    @Override
    public String querySlname(String slname) {
        return prodMapper.querySlname(slname);
    }

    @Override
    public List<String> queryProd(Map map) {
        return prodMapper.queryProd(map);
    }

    @Override
    public int chaAccept(String accept,String depaname) {
        return prodMapper.chaAccept(accept,depaname);
    }
}
