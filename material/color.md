Trong Java, bạn có thể sử dụng các mã màu ANSI Escape để thay đổi màu sắc của văn bản trong console. Dưới đây là một số mã màu ANSI Escape thường được sử dụng:

1. Màu chữ:

    - Đen: `\u001B[30m`
    - Đỏ: `\u001B[31m`
    - Xanh lục: `\u001B[32m`
    - Vàng: `\u001B[33m`
    - Xanh da trời: `\u001B[34m`
    - Tím: `\u001B[35m`
    - Xanh lam: `\u001B[36m`
    - Trắng: `\u001B[37m`

2. Màu nền:

    - Đen: `\u001B[40m`
    - Đỏ: `\u001B[41m`
    - Xanh lục: `\u001B[42m`
    - Vàng: `\u001B[43m`
    - Xanh da trời: `\u001B[44m`
    - Tím: `\u001B[45m`
    - Xanh lam: `\u001B[46m`
    - Trắng: `\u001B[47m`

3. Màu chữ đậm:

    - Đen: `\u001B[1;30m`
    - Đỏ: `\u001B[1;31m`
    - Xanh lục: `\u001B[1;32m`
    - Vàng: `\u001B[1;33m`
    - Xanh da trời: `\u001B[1;34m`
    - Tím: `\u001B[1;35m`
    - Xanh lam: `\u001B[1;36m`
    - Trắng: `\u001B[1;37m`

Để sử dụng mã màu trong console, bạn chỉ cần thêm mã Escape tương ứng vào chuỗi mà bạn muốn hiển thị. Ví dụ:


Lưu ý rằng khôi phục màu mặc định (`\u001B[0m`) sẽ đặt màu chữ và nền trở về màu mặc định.