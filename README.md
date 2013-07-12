VRaptor-Breadcrumb
======================

A simple Breadcrumb plugin for VRaptor projects.

Requirements
============

VRaptor 3.x

Install
=======

You only need to copy the jar to your classpath. VRaptor will register plugin when 
your application starts without any configurations. Available in Maven Repository:

    <dependency>
        <groupId>br.com.breadcrumb</groupId>
        <artifactId>vraptor-breadcrumb</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Usage
=====

Controller
----------

All methods annotated with @BreadCrumb annotation will be available in the view.

    @Resource
    public class IndexController {

        @Get
        @BreadCrumb(message = "some.key", level = 0)
        public void index() {

        }

        @Get
        public void noBreadcrumb() {

        }
    };

JSP
-----

To add the breadcrumb in jsp, you just need to add the user's instance or someone else to a new session like that:

    <ul class="breadcrumb">
        <c:forEach items="${breadcrumbSession.itens}" var="item"
            varStatus="status">
            <c:choose>
                <c:when
                    test="${status.index == fn:length(bd.itens)-1 && status.index!=0}">
                    <li id="current">> ${item.nome}</li>
                </c:when>
                <c:otherwise>
                    <li>> <a href="<c:url value="${item.uri}"/>">${item.nome}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>

Author
======

Pablo Souza - pvrsouza@gmail.com


Thanks
======

Danilo Viana
