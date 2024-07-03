package com.example.DAMH.Controller;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.Service.CHITIETDATHANGService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CHITIETDATHANGController {

    @Autowired
    private CHITIETDATHANGService chitietdathangService;

    @GetMapping("/chitietdathang")
    public String viewChiTietDatHang(Model model) {
        List<CHITIETDATHANG> chitietdathangList = chitietdathangService.getAllChiTietDatHang();
        model.addAttribute("chitietdathangList", chitietdathangList);
        return "chitietdathang/chitietdathanglist";
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportToExcel(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws IOException {

        List<CHITIETDATHANG> chitietdathangList = chitietdathangService.getChiTietDatHangByDateRange(startDate, endDate);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Chi Tiết Đặt Hàng");

        // Tạo hàng tiêu đề
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Mã CTDH", "Số Lượng Đặt", "Ngày Giao Dự Kiến", "Địa Chỉ", "Giá Đặt", "Tổng Đặt", "Sản Phẩm", "Đơn Đặt Hàng"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Tạo các hàng dữ liệu và tính tổng tiền
        int rowNum = 1;
        double totalAmount = 0.0;
        for (CHITIETDATHANG chitiet : chitietdathangList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(chitiet.getMaCTDH());
            row.createCell(1).setCellValue(chitiet.getSoLuongDat());
            row.createCell(2).setCellValue(chitiet.getNgayGiaoDuKien().toString());
            row.createCell(3).setCellValue(chitiet.getDiaChi());
            row.createCell(4).setCellValue(chitiet.getGiaDat());
            row.createCell(5).setCellValue(chitiet.getTongDat());
            row.createCell(6).setCellValue(chitiet.getSanpham().getBarcode());
            row.createCell(7).setCellValue(chitiet.getDondathang().getMaDon());

            totalAmount += chitiet.getTongDat();
        }

        // Thêm hàng hiển thị tổng tiền
        Row totalRow = sheet.createRow(rowNum);
        Cell totalLabelCell = totalRow.createCell(4);
        totalLabelCell.setCellValue("Tổng Tiền");
        Cell totalAmountCell = totalRow.createCell(5);
        totalAmountCell.setCellValue(totalAmount);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        byte[] bytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=chitietdathang.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}
