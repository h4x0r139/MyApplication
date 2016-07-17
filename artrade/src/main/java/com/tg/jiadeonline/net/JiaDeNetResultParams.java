package com.tg.jiadeonline.net;

public class JiaDeNetResultParams
{
  public static final String isnotlogin = "1";
  public static final String success = "true";

  public static String getResultMsg(String paramString)
  {
    String str = "无法解析返回值";
    if (paramString.equals("0"))
      str = "交易成功";
    do
    {
      return str;
      if (paramString.equals("1"))
        return "用户没有登陆";
      if (paramString.equals("2"))
        return "服务器出错";
      if (paramString.equals("3"))
        return "网络异常";
      if (paramString.equals("4"))
        return " 用户名或密码错误";
      if (paramString.equals("5"))
        return "用户名邮箱已存在";
      if (paramString.equals("6"))
        return "用户名邮箱格式不合法";
      if (paramString.equals("7"))
        return "身份证号不合法";
      if (paramString.equals("8"))
        return "身份证号已经存在";
      if (paramString.equals("9"))
        return "收货人不合法";
      if (paramString.equals("10"))
        return "收货人未填";
      if (paramString.equals("11"))
        return "邮编不合法";
      if (paramString.equals("12"))
        return "手机号不合法";
      if (paramString.equals("13"))
        return "该商品已添加愿望清单,不能重复添加";
      if (paramString.equals("14"))
        return "用户未设置地址";
      if (paramString.equals("15"))
        return "未找到相关地址信息";
      if (paramString.equals("16"))
        return "没有找到人员信息";
      if (paramString.equals("17"))
        return "邮箱已经存在";
      if (paramString.equals("18"))
        return "头像为空";
      if (paramString.equals("19"))
        return "昵称已经存在";
      if (paramString.equals("20"))
        return "对应地址已删除，不能重复删除";
      if (paramString.equals("21"))
        return "旧密码错误";
      if (paramString.equals("22"))
        return "最多输入100个汉字";
      if (paramString.equals("23"))
        return "要删除的客户消息为空";
      if (paramString.equals("24"))
        return "未查询到该属性的商品";
      if (paramString.equals("25"))
        return "未查询到相关客户消息";
      if (paramString.equals("26"))
        return "未找到商品";
      if (paramString.equals("27"))
        return "商品已经达到限购数量";
      if (paramString.equals("28"))
        return "商品已经售罄";
      if (paramString.equals("29"))
        return "商品已经下架";
      if (paramString.equals("30"))
        return " 商品已经删除";
      if (paramString.equals("31"))
        return " 商品已经失效";
      if (paramString.equals("32"))
        return "订单不存在";
      if (paramString.equals("33"))
        return "没有找到杂志信息";
      if (paramString.equals("34"))
        return "商品不存在";
      if (paramString.equals("35"))
        return "没有找到往期杂志";
      if (paramString.equals("36"))
        return "商品库存不足";
      if (paramString.equals("37"))
        return "商品可能在购物车中已经删除";
      if (paramString.equals("38"))
        return "请求参数不合法";
      if (paramString.equals("39"))
        return "结果未查询到";
      if (paramString.equals("40"))
        return "未设置邮箱";
      if (paramString.equals("41"))
        return "邮箱未激活";
      if (paramString.equals("42"))
        return "链接已失效";
      if (paramString.equals("43"))
        return "邮箱已激活";
      if (paramString.equals("44"))
        return "暂时不支持此功能";
      if (paramString.equals("45"))
        return "积分不够";
      if (paramString.equals("46"))
        return "没有默认地址";
      if (paramString.equals("47"))
        return "后台处理错误";
      if (paramString.equals("48"))
        return "邀请码错误";
      if (paramString.equals("50"))
        return "账户不存在";
      if (paramString.equals("51"))
        return "第三方登录待绑定用户已被绑定";
      if (paramString.equals("52"))
        return "第三方登录用户已绑定过其它用户";
      if (paramString.equals("53"))
        return "代金券不存在或已不可用";
      if (paramString.equals("54"))
        return "订单状态不支持退货";
      if (paramString.equals("55"))
        return "查询订单信息发生错误:未获取到订单明细信息";
      if (paramString.equals("56"))
        return "查询订单信息发生错误:未查询到订单商品属性";
      if (paramString.equals("57"))
        return "库存不足,去看看其他商品吧！";
      if (paramString.equals("58"))
        return "下单商品已经超过海关规定的限购数量，请修改后重新操作";
      if (paramString.equals("59"))
        return "验证限购数量出错，请返回重新提交";
      if (paramString.equals("60"))
        return "查询订单信息发生错误：计算到手价出现异常";
    }
    while (!paramString.equals("61"));
    return "订单状态出错";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.JiaDeNetResultParams
 * JD-Core Version:    0.6.2
 */