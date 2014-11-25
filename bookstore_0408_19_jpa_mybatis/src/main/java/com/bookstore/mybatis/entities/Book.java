package com.bookstore.mybatis.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Book {
	
	private @Getter @Setter int id;
	private @Getter @Setter String name;
	private @Getter @Setter String author;
	private @Getter @Setter Date publishDate;
	private @Getter @Setter String comment;

	//	private @Getter @Setter Integer bookStatus;
	/* resultMap Enum 타입 사용 시
	 * 아래의 엘리먼트를 사용한다. 
	 * <result property="bookStatus" column="status" typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler"/>
	 * */
	private @Getter @Setter BookStatus bookStatus;
	private @Getter @Setter Integer rentUserId;
	
	private @Getter @Setter List<History> histories = new ArrayList<History>();	
	
}
