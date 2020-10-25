package com.zzz.fun.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import org.junit.Before;
import org.junit.Test;

// PropertyPreFilter --> PropertyFilter --> NameFilter --> ValueFilter --> BeforeFilter --> AfterFilter
public class JSONFilterTest {

    private static final String dataStr = "{\"data\":{\"detail_01\":\"ew\",\"detail_02\":\"2\",\"detail_03\":\"34\",\"detail_04\":\"25\"," +
            "\"detail_05\":\"66\",\"detail_06\":\"ew\",\"detail_07\":\"qw\",\"detail_08\":\"ew\",\"detail_09\":\"12\",\"detail_10\":\"34\"," +
            "\"detail_11\":\"qw\",\"detail_12\":\"53\",\"detail_13\":\"34\",\"detail_14\":\"4\",\"detail_15\":\"55\",\"detail_16\":\"66\"," +
            "\"detail_17\":\"9\",\"detail_18\":\"99\",\"detail_19\":\"ew\",\"detail_20\":\"ew\",\"detail_21\":\"565\",\"detail_22\":\"ew\"," +
            "\"detail_23\":\"qw\",\"detail_24\":\"4\",\"detail_25\":\"444\",\"detail_26\":\"56\",\"detail_27\":\"4545\"," +
            "\"detail_28\":\"432\",\"detail_29\":\"qw\",\"detail_30\":\"ew\",\"detail_31\":\"23\",\"id\":\"001\"," +
            "\"name\":\"test\",\"value\":\"你好 白菜\"}}";

    // source
    private static final JSONObject dataObj = JSONObject.parseObject(dataStr);

    // propertyPreFilter (这个filter本身，被过滤的obj，相应key)
    private static final PropertyPreFilter propertyPreFilter = (jsonSerializer, source, key) -> key.startsWith("detail_");

    // 与propertyPreFilter相同功能(被过滤的obj，相应key，相应value)
    private static final PropertyFilter propertyFilter = (source, key, value) -> key.startsWith("detail_");

    // PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化；
    private static final PropertyFilter propertyFilterKV = (source, key, value) -> {
        boolean keyResult = key.startsWith("detail_");
        boolean valueResult;
        try {
            Integer.parseInt((String) value);
            valueResult = true;
        } catch (NumberFormatException | ClassCastException e) {
            valueResult = false;
        }
        return keyResult && valueResult;
    };

    // NameFilter 修改Key，如果需要修改Key，process返回值则可；
    private static final NameFilter nameFilter = (source, key, value) -> key.replace("_", "");

    // ValueFilter 修改Value；
    private static final ValueFilter valueFilter = (source, key, value) -> key + value;

    // BeforeFilter 序列化时在最前添加内容
    private static final BeforeFilter beforeFilter = new BeforeFilter() {
        @Override
        public void writeBefore(Object obj) {
            Data data = (Data) obj;
            System.out.println("----BeforeFilter------------object=" + obj);
            data.setName("we");
            data.setId("1234");
        }
    };

    // AfterFilter 序列化时在最后添加内容。
    private static final AfterFilter afterFilter = new AfterFilter() {
        @Override
        public void writeAfter(Object obj) {
            Data data = (Data) obj;
            System.out.println("----AfterFilter------------object=" + obj);
            data.setName("zc");
            data.setId("34322");
        }
    };

    @Before
    public void before(){
        System.out.println("source：" + dataObj);
    }

    /**
     * PropertyPreFilter 根据PropertyName判断是否序列化；
     */
    @Test
    public void executePropertyPreFilterTest() {
        // 过滤只针对首层节点，更深层的接口不会被过滤
        String jsonString = JSON.toJSONString(dataObj, propertyPreFilter);
        System.out.println("第一层只有data，没有节点被过滤出：" + JSONObject.parseObject(jsonString));
        jsonString = JSON.toJSONString(dataObj.getJSONObject("data"), propertyPreFilter);
        System.out.println("第二层节点被过滤出：" + JSONObject.parseObject(jsonString));
    }

    /**
     * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化；
     */
    @Test
    public void executePropertyFilterTest() {
        String jsonString = JSON.toJSONString(dataObj.getJSONObject("data"), propertyFilter);
        System.out.println("与PropertyPreFilter相同功能，过滤结果：" + JSONObject.parseObject(jsonString));
        jsonString = JSON.toJSONString(dataObj.getJSONObject("data"), propertyFilterKV);
        System.out.println("根据PropertyName和PropertyValue来判断，过滤结果：" + JSONObject.parseObject(jsonString));
    }


    /**
     * NameFilter 修改Key，如果需要修改Key，process返回值则可；
     */
    @Test
    public void executeNameFilterTest() {
        String jsonString = JSON.toJSONString(dataObj.getJSONObject("data"), nameFilter);
        System.out.println("修改key后结果：" + JSONObject.parseObject(jsonString));
    }

    /**
     * ValueFilter  修改Value；
     */
    @Test
    public void executeValueFilterTest() {
        String jsonString = JSON.toJSONString(dataObj.getJSONObject("data"), valueFilter);
        System.out.println("修改value后结果：" + JSONObject.parseObject(jsonString));
    }


    /**
     * BeforeFilter 序列化时在最前添加内容；
     */
    @Test
    public void executeBeforeFilterTest() {
        Data data = JSON.parseObject(dataObj.getString("data"), Data.class);
        // BeforeFilter 传入参数不能是JSONObject类型
        String jsonString = JSON.toJSONString(data, beforeFilter);
        System.out.println("添加节点后结果：" + JSONObject.parseObject(jsonString));
    }

    /**
     * AfterFilter 序列化时在最后添加内容。
     */
    @Test
    public void executeAfterFilterTest() {
        Data data = JSON.parseObject(dataObj.getString("data"), Data.class);
        // AfterFilter 传入参数不能是JSONObject类型
        String jsonString = JSON.toJSONString(data, afterFilter);
        System.out.println("不影响结果：" + JSONObject.parseObject(jsonString));
        System.out.println("影响原引用：" + JSONObject.toJSONString(data));
    }


  static class Data {
      private String id;
       private String name;

      String getId() {
          return id;
      }

      void setId(String id) {
          this.id = id;
      }

      String getName() {
          return name;
      }

      void setName(String name) {
          this.name = name;
      }
  }
}



