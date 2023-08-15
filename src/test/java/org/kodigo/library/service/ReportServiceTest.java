package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.repository.IReserveRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @Mock
    private IReserveRepository reserveRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void testExportReport_PDF() throws FileNotFoundException, JRException {
        String reportFormat = "pdf";
        String expectedReportPath = "C:\\Users\\Alfredo Alas\\Desktop\\Reportes\\reserves.pdf";

        List<Reserve> reserves = new ArrayList<>();
        reserves.add(new Reserve());
        when(reserveRepository.findAll()).thenReturn(reserves);

        String result = reportService.exportReport(reportFormat);

        assertEquals("Report generated in path : " + expectedReportPath, result);
        verify(reserveRepository, times(1)).findAll();
    }

    @Test
    public void testExportReport_HTML() throws FileNotFoundException, JRException {
        String reportFormat = "html";
        String expectedReportPath = "C:\\Users\\Alfredo Alas\\Desktop\\Reportes\\reserves.html";

        List<Reserve> reserves = new ArrayList<>();
        reserves.add(new Reserve());
        when(reserveRepository.findAll()).thenReturn(reserves);

        String result = reportService.exportReport(reportFormat);

        assertEquals("Report generated in path : " + expectedReportPath, result);
        verify(reserveRepository, times(1)).findAll();
    }

}

