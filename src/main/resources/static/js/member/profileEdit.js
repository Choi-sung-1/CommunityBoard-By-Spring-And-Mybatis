function previewImage(event){
    const file = event.target.files[0];
    const reader = new FileReader();
    const originalImage = document.getElementById('profileImage');
    originalImage.hidden = true;

    reader.onload = function (e){
        const preview = document.getElementById("preview");
        const previewCard = document.getElementById("previewCard");
        preview.src = e.target.result;
        previewCard.style.display="block";
    };
    if(file){
        reader.readAsDataURL(file);
    }
}

function validator(event) {
    let isValid = true;

    // 이름 검증
    const nameInput = document.getElementById("memberName");
    const nameError = document.getElementById("nameError");
    const name = nameInput.value.trim();
    if (name.length < 2) {
        nameError.textContent = "이름은 최소 2자 이상 입력해야 합니다.";
        nameInput.classList.add("is-invalid");
        isValid = false;
    } else {
        nameError.textContent = "";
        nameInput.classList.remove("is-invalid");
    }

    // 전화번호 검증
    const phoneInput = document.getElementById("memberPhone");
    const phoneError = document.getElementById("phoneError");
    const phone = phoneInput.value.trim();
    const phoneRegex = /^010-\d{4}-\d{4}$/;

    if (phone !== "" && !phoneRegex.test(phone)) {
        phoneError.textContent = "전화번호는 010-1234-5678 형식이어야 합니다.";
        phoneInput.classList.add("is-invalid");
        isValid = false;
    } else {
        phoneError.textContent = "";
        phoneInput.classList.remove("is-invalid");
    }

    // 이메일 검증
    const emailInput = document.getElementById("memberEmail");
    const emailError = document.getElementById("emailError");
    const email = emailInput.value.trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email !=="" && !emailRegex.test(email)) {
        emailError.textContent = "유효한 이메일 형식을 입력하세요.";
        emailInput.classList.add("is-invalid");
        isValid = false;
    } else {
        emailError.textContent = "";
        emailInput.classList.remove("is-invalid");
    }

    if (!isValid) {
        event.preventDefault();
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    form.addEventListener("submit", validator);
});