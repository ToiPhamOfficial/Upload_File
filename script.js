// Thiết lập khoảng thời gian nghỉ (tính bằng giây)
const minDelay = 5; 
const maxDelay = 7; 

// Tính toán thời gian ngẫu nhiên
const randomSeconds = Math.floor(Math.random() * (maxDelay - minDelay + 1)) + minDelay;
const delayMs = randomSeconds * 1000;

// Sử dụng return để Automa đợi cho đến khi resolve()
return new Promise((resolve) => {
    setTimeout(() => {
        resolve(); // Khi gọi resolve, Automa tự động nhảy sang block kế tiếp
    }, delayMs);
});
