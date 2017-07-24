<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="choose" class="org.apache.struts.maliweb.action.ChooseAction" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Malicious Web</title>
</head>
<body>

<s:form action="malicious">
    <s:radio label="Malices: " name="yourMalice" list="malis" value="%{yourMalice}"/>
    <s:textfield label="Time" name="howLong" value="%{howLong}"/>
    <s:textfield label="From String" name="fromString" value="%{fromString}"/>
        <s:property value="#blanks" escapeHtml="false" />
    <s:textfield label="Change To String" name="changeToString" value="%{changeToString}"/>
    <s:textfield label="MQ Adres" name="mqAddress" value="%{mqAddress}"/>
    <s:submit/>
</s:form>

<table border="0" cellspacing="0" cellpadding="1">
    <tr>
        <th>Log</th>
    </tr>

    <s:iterator value="dataHis.eventHistory" status="rowstatus">
        <tr>
            <s:if test="#rowstatus.odd == true">
                <td style="background:  #d9d9d9 "><s:property value="time"/> = <s:property value="name"/></td>
            </s:if>
            <s:else>
                <td><s:property value="time"/> = <s:property value="name"/></td>
            </s:else>
        </tr>
    </s:iterator>
</table>
</body>
</html>