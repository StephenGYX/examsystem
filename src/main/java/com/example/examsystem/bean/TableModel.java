package com.example.examsystem.bean;

import java.util.List;

public class TableModel
{
	private int count;
	private String msg;
	private int code;
	private List<?> data;

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public List<?> getData()
	{
		return data;
	}

	public void setData(List<?> data)
	{
		this.data = data;
	}
}