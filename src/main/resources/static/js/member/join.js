const loginInput = document.getElementById("loginId");
const loginIdMessage = document.getElementById("loginIdMessage");
const duplicateCheckButton = document.getElementById("duplicateCheckButton");
let isDuplicateChecked = false;

duplicateCheckButton.addEventListener("click", () => {
    const value = loginInput.value.trim();

    if (value.length === 0) {
        alert("아이디를 입력해주세요.");
        return;
    } else if (value.length < 5 || value.length > 20) {
        alert("아이디는 5글자 이상 20글자 이하만 입력할 수 있습니다.");
        return;
    } else if (!/^[a-zA-Z0-9]+$/.test(value)) {
        alert("아이디는 영문자와 숫자만 사용할 수 있습니다.");
        return;
    }

    // 서버 중복 체크
    fetch(`/member/duplicate?memberLoginId=${encodeURIComponent(value)}`)
        .then(res => res.json())
        .then(data => {
            if(data.available){
                loginIdMessage.textContent = "✅ 사용 가능한 아이디입니다.";
                loginIdMessage.style.color = "green";
                isDuplicateChecked = true;
            } else {
                loginIdMessage.textContent = "❌ 이미 사용 중인 아이디입니다.";
                loginIdMessage.style.color = "red";
                isDuplicateChecked = false;
            }
        })
        .catch(err => console.error("에러 발생:", err));
});

const form = document.querySelector("form");
form.addEventListener("submit",(e)=>{
    if (!isDuplicateChecked){
        e.preventDefault();
        if (loginInput.value.trim()===''){
            alert("⚠️ 필수입력사항을 먼저 기재해주세요.");
        }else{
            alert("⚠️ 아이디 중복 체크를 먼저 해주세요.");
        }
    }
});