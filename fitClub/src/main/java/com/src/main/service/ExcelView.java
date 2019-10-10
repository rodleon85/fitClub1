package com.src.main.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.src.main.entities.Aluno;

public class ExcelView extends AbstractXlsView{
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
	                                  Workbook workbook,
	                                  HttpServletRequest request,
	                                  HttpServletResponse response) throws Exception {

	    response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_inadimplentes.xls\"");

	    @SuppressWarnings("unchecked")
	    List<Aluno> listaAlunoInadimplentes = (List<Aluno>) model.get("alunosInadimplentes");

	    Sheet sheet = montarArquivoXls(workbook);

	    popularArquivoInadimplentes(listaAlunoInadimplentes, sheet);



	}


	private Sheet montarArquivoXls(Workbook workbook) {
	    Sheet sheet = workbook.createSheet("Alunos Inadimplentes");
	    sheet.setDefaultColumnWidth(30);

	    CellStyle style = workbook.createCellStyle();
	    Font font = workbook.createFont();
	    font.setFontName("Arial");
	    style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    font.setBold(true);
	    font.setColor(HSSFColorPredefined.WHITE.getIndex());
	    style.setFont(font);


	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("Nome");
	    header.getCell(0).setCellStyle(style);
	    header.createCell(1).setCellValue("CPF");
	    header.getCell(1).setCellStyle(style);
	    header.createCell(2).setCellValue("RG");
	    header.getCell(2).setCellStyle(style);
	    header.createCell(3).setCellValue("Telefone");
	    header.getCell(3).setCellStyle(style);
	    header.createCell(4).setCellValue("Endereço");
	    header.getCell(4).setCellStyle(style);
	    header.createCell(5).setCellValue("E-mail");
	    header.getCell(5).setCellStyle(style);
	    header.createCell(6).setCellValue("Data de Vencimento");
	    header.getCell(6).setCellStyle(style);
	    header.createCell(7).setCellValue("Status");
	    header.getCell(7).setCellStyle(style);
		return sheet;
	}
	
	private void popularArquivoInadimplentes(List<Aluno> listaAlunoInadimplentes, Sheet sheet) {
		int rowCount = 1;

	    for(Aluno alunoInadimplente : listaAlunoInadimplentes){
	        Row alunoRow =  sheet.createRow(rowCount++);
	        alunoRow.createCell(0).setCellValue(alunoInadimplente.getName());
	        alunoRow.createCell(1).setCellValue(alunoInadimplente.getCpf());
	        alunoRow.createCell(2).setCellValue(alunoInadimplente.getRg());
	        alunoRow.createCell(3).setCellValue(alunoInadimplente.getTelefone());
	        alunoRow.createCell(4).setCellValue(alunoInadimplente.getEndereco());
	        alunoRow.createCell(5).setCellValue(alunoInadimplente.getEmail());
	        alunoRow.createCell(6).setCellValue(alunoInadimplente.getDataProximoPagamento());
	        alunoRow.createCell(7).setCellValue(alunoInadimplente.getStatus());

	        }
	}

}
