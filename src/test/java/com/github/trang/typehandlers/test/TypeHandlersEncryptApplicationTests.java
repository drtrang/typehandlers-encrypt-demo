package com.github.trang.typehandlers.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.trang.typehandlers.TypeHandlersEncryptApplication;
import com.github.trang.typehandlers.domain.BaseCode;
import com.github.trang.typehandlers.mapper.BaseCodeMapper;
import com.github.trang.typehandlers.mapper.BaseCodeOriginMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TypeHandlersEncryptApplication.class)
@Slf4j
public class TypeHandlersEncryptApplicationTests {

    @Autowired
    private BaseCodeMapper mapper;
    @Autowired
    private BaseCodeOriginMapper originMapper;

    Gson gson = new Gson();

    /**
     * 因为在 #{@link TypeHandlersEncryptApplication#run(String...)} 中已经插入数据了，所以这里只是查询
     */
    @Test
    public void insert() {
        List<BaseCode> baseCodes = mapper.selectAll();
        baseCodes.stream().map(gson::toJson).forEach(s -> log.info("decrypt: {}", s));
        assertEquals(4, baseCodes.size());

        List<BaseCode> originBaseCodes = originMapper.selectAll();
        originBaseCodes.stream().map(gson::toJson).forEach(s -> log.info("origin:  {}", s));
        assertEquals(4, originBaseCodes.size());
    }

    @Test
    public void update() {
        BaseCode record = new BaseCode();
        record.setCodeType("update");
        record.setCodeValue("update");
        record.setRemark("update");
        mapper.insert(record);

        BaseCode param = new BaseCode();
        param.setId(record.getId());
        param.setCodeType("newUpdate");
        param.setCodeValue("newUpdate");
        param.setRemark("newUpdate");
        mapper.updateByPrimaryKey(param);

        log.info("decrypt: {}", gson.toJson(mapper.selectByPrimaryKey(record.getId())));
        log.info("origin:  {}", gson.toJson(originMapper.selectByPrimaryKey(record.getId())));
    }

    @Test
    public void selectForResult() {
        // 临时新增一条测试数据，其中 remark 字段会加密后再保存到数据库中
        BaseCode record = new BaseCode();
        record.setCodeType("selectForResult");
        record.setCodeValue("selectForResult");
        record.setRemark("selectForResult");
        mapper.insert(record);
        log.info("decrypt: {}", gson.toJson(mapper.selectByPrimaryKey(record.getId())));
        log.info("origin:  {}", gson.toJson(originMapper.selectByPrimaryKey(record.getId())));
    }

    @Test
    public void selectForCondition() {
        // 临时新增一条测试数据，其中 remark 字段会加密后再保存到数据库中
        BaseCode record = new BaseCode();
        record.setCodeType("selectForCondition");
        record.setCodeValue("selectForCondition");
        record.setRemark("selectForCondition");
        mapper.insert(record);
        log.info("decrypt: {}", gson.toJson(mapper.selectByPrimaryKey(record.getId())));
        log.info("origin:  {}", gson.toJson(originMapper.selectByPrimaryKey(record.getId())));

        // 根据 remark='selectForCondition' 的条件查询，断言查询结果中有 1 条数据，因为 mapper 的 sql 中指明了对 remark 字段加密后再查询
        List<BaseCode> baseCodes = mapper.selectByRemark("selectForCondition");
        baseCodes.stream().map(gson::toJson).forEach(s -> log.info("decrypt: {}", s));
        assertEquals(1, baseCodes.size());

        // 根据 remark='selectForCondition' 的条件查询，断言查询结果中没有数据，因为 originMapper 的 sql 中没有对 remark 字段做加密处理
        List<BaseCode> originBaseCodes = originMapper.selectByRemark("selectForCondition");
        originBaseCodes.stream().map(gson::toJson).forEach(s -> log.info("origin:  {}", s));
        assertEquals(0, originBaseCodes.size());
    }

}