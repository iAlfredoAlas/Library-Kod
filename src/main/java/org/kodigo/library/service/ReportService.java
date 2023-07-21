package org.kodigo.library.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.repository.IReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private IReserveRepository reserveRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Alfredo Alas\\Desktop\\Reportes";
        List<Reserve> reserves=reserveRepository.findAll();

        File file= ResourceUtils.getFile("classpath:reserves.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reserves);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Group 2");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\reserves.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\reserves.pdf");
        }

        return "Report generated in path : " + path;
    }
}
