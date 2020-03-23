package com.example.examsystem.service.student;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.dto.ExamDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ExamScoreService
{
	public List<Subject> searchType();
	public Results<ExamDto> testTable(HttpSession session, PageTableRequest request);
}
