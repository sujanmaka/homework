<%-- 
    Document   : index
    Created on : Nov 25, 2019, 1:05:02 PM
    Author     : sujan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style> 
        input[type=text] {
            width: 50%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 1px solid red;
            border-radius: 4px;
        }

    </style>
    <body>
        Enter Head master info to be saved.
        <form action="NewServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Name: </td>
                        <td><input type="text" name="name" value="" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Age: </td>
                        <td><input type="text" name="age" value="" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Education: </td>
                        <td><input type="text" name="education" value="" size="50"/></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
