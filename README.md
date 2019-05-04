# easyexcel-wraper

## easyexcel-wraper是什么?

一个方便读取excel内容，且可以使用注解进行内容验证的包装工具

## easyexcel-wraper有哪些功能？

* 在easyexcel的基础上进行封装，方便读取excel内容，避免在主业务代码中嵌入重复繁琐的样本代码
* 支持Hibernate-validator验证框架，可使用诸如@NotBlank,@NotDuiplicate的注解

## 如何使用

* 1、新建一个JavaBean用于接收excel内容，并继承ExcelRow基础类,如：新建MyRow.java

```java
import com.alibaba.excel.annotation.ExcelProperty;
import com.wuyue.excel.ExcelRow;
import com.wuyue.excel.validate.NotDuplicate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class MyRow extends ExcelRow {

    @ExcelProperty(index = 0)
    @NotDuplicate
    @NotBlank(message = "名称不能为空")
    private String name;

    @ExcelProperty(index = 1)
    @Email
    private String email;

}

```

* 2、调用ExcelUtil.read方法,获取excel内容

```java
    File file = new File("D:\\1.xlsx");
    FileInputStream fileInputStream = new FileInputStream(file);
    List<MyRow> rows = ExcelReader.builder()
            .inputStream(fileInputStream)
            .sheetNo(1)
            .headLineMun(1)
            .build()
            .read(MyRow.class);
```

* 3、主业务功能代码使用“行”内容的校验结果

```java
    System.out.println(rows);
    rows.forEach(row -> {
        // 行号，如果要提示实际excel行号，应该要加上headLineMun的值
        System.out.print("Row number:" + row.getRowNum());
        // 校验结果代码(0为正常)
        System.out.print(", validate code:" + row.getValidateCode());
        // 校验结果内容
        System.out.println(", message:" + row.getValidateMessage());
    });
```
当D:\\1.xlsx的内容为如下时<br/>
![text](https://github.com/Felix0525/assets/blob/master/20190501-0001.png?raw=true)

输出结果如下：<br/>

```java
    [MyRow(name=felix, email=5401142), MyRow(name=wuyue, email=540114289@qq.com), MyRow(name=felix, email=null), MyRow(name=wuyue, email=null)]
    Row number:1, validate code:2, message:不是一个合法的电子邮件地址
    Row number:2, validate code:0, message:null
    Row number:3, validate code:1, message:Duplicate field
    Row number:4, validate code:1, message:Duplicate field
```
