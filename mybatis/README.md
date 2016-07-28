#mybatis Generator逆向工程 说明：
    @Date: 2016-7-20 16:16:09 @Author: ebbgar @email:edgar_zhang2014@163.com

    使用：@Date: 2016-7-21 10:47:26 @Author: edgar
        1.配置 mybatis-generator-config.xml 逆向工程所需配置，
            该配置文件每个标签有详细说明，附带dtd约束文件以下载；
        2.修改xml文件对应生成“entity实体类、mybatis映射xml文件、mapper接口”的具体位置；
        3.生成的表 --- 如果有多个表，复制这一段，改下表名即可

    使用方式：
       （1） mvn： mybatis-generator:generate；
       （2） 执行官方提供的逆向工程执行程序，详情见 ：GeneratorSqlmaq.java，
            执行完后，刷新项目.

    使用技巧：
      a) 建表时，字段名称建议用"_"分隔多个单词，比如:AWB_NO、REC_ID...，这样生成的entity，属性名称就会变成漂亮的驼峰命名，即：awbNo、recId
      b)oracle中，数值形的字段，如果指定精度，比如Number(12,2)，默认生成entity属性是BigDecimal型 ，如果不指定精度，比如:Number(9)，指默认生成的是Long型
      c)oracle中的nvarchar/nvarchar2，mybatis-generator会识别成Object型，建议不要用nvarchar2，改用varchar2

    注意事项：
        Mapper映射文件已经存在时，如果进行重新生成则mapper.xml文件时，
      内容不被覆盖而是进行内容追加，结果导致mybatis解析失败。
      故需要重新生成代码时，先删除原来已经生成的mapper xml文件再进行生成。
      Mybatis自动生成的pojo类及对应的mapper.java文件不是内容
      而是直接覆盖没有此问题。

