package com.example.DAMH;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum ChucVu {
    ADMIN(1), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    MANAGER(2),
    EMPLOYEE(3);
    // Vai trò người dùng bình thường, có quyền hạn giới hạn.
    public final long value; // Biến này lưu giá trị số tương ứng với mỗi vai trò.
}
