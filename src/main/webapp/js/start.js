function updateTime() {
    let now = new Date()
    document.querySelector('.time').innerHTML = now.toLocaleTimeString()
}

setInterval(updateTime, 11000);
window.onload = updateTime;
