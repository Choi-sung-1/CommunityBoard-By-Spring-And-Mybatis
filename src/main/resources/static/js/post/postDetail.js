document.addEventListener("DOMContentLoaded", function () {
    const likeStatusElement = document.getElementById("likeStatus");
    const likeStatus = likeStatusElement.dataset.likeStatus;
    updateLikeButtonStyle(likeStatus);
});

//좋아요 버튼 클릭
function onClickLikeButton() {
    // 초기 liked 값 설정
    let liked = document.getElementById('likeStatus').dataset.status === 'onClick';
    liked = !liked; // 상태 뒤집기
    const likeButtonStatus = liked ? 'onClick' : 'noneClick';
    const likeCount = document.getElementById('likeCount');
    const postId = document.getElementById('postId').dataset.postId;
    const memberId = document.getElementById('memberId').dataset.memberId;

    // 서버 요청
    fetch('/post/like', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            postId: Number(postId),
            memberId: Number(memberId),
            likeButtonStatus: likeButtonStatus
        })
    })
        .then(response => response.json())
        .then(data => {
            likeCount.textContent = data.likeCount;
            updateLikeButtonStyle(data.likeStatus);
        });
}

//게시글 삭제 컨펌
function deleteOnclick() {
    return confirm("정말 삭제하시겠습니까?");
}
// 댓글 작성
function onClickCommentButton() {
    let commentContent = document.getElementById('commentContent').value;
    const postId =document.getElementById('postId').dataset.postId;
    const memberId =document.getElementById('memberId').dataset.memberId

    if (!commentContent || commentContent.trim() === '') {
        // 공백 또는 비어 있는 댓글일 때 처리
        alert("댓글을 작성해주세요.");
        return;
    }
    fetch('/comment/add',{
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body:JSON.stringify({
            postId:Number(postId),
            memberId:Number(memberId),
            commentContent:commentContent
        })
    }).then(response=>response.json())
        .then(comment=>{
            appendCommentList(comment);
            document.getElementById('commentContent').value ="";

        })
}
// 댓글 요소 추가
function appendCommentList(comment) {
    const commentList = document.getElementById('commentList');

    const commentBox = document.createElement("div");
    commentBox.classList.add("commentBox");
    commentBox.dataset.commentId = comment.id;

    // 상단 작성자 + 날짜 + 삭제 버튼
    const topRow = document.createElement("div");
    topRow.classList.add("d-flex", "justify-content-between", "align-items-center", "mb-2");

    const writerDiv = document.createElement("div");
    const strong = document.createElement("strong");
    strong.textContent = comment.commentWriterName;
    const small = document.createElement("small");
    small.textContent = comment.commentRegisterDate;
    small.classList.add("text-muted", "ms-2");

    writerDiv.appendChild(strong);
    writerDiv.appendChild(small);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "🗑 삭제";
    deleteButton.onclick = function () { deleteComment(deleteButton); };
    deleteButton.classList.add("btn", "btn-sm", "btn-outline-danger");

    topRow.appendChild(writerDiv);
    topRow.appendChild(deleteButton);

    // 댓글 내용
    const contentTextarea = document.createElement("textarea");
    contentTextarea.setAttribute("readonly", true);
    contentTextarea.textContent = comment.commentContent;
    contentTextarea.classList.add("form-control");
    contentTextarea.rows = 2;

    commentBox.appendChild(topRow);
    commentBox.appendChild(contentTextarea);

    // 동적으로 추가
    commentList.appendChild(commentBox);
}

// 댓글 삭제
function deleteComment(button) {
    const commentBox = button.closest('.commentBox'); // 가장 가까운 부모 div 찾기
    const commentId = commentBox.dataset.commentId;

    fetch(`/comment/delete/${commentId}`, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            throw new Error("삭제 실패");
        }
        return response.json(); // OK 응답일 경우
    });
    commentBox.remove();
//     삭제는되는데 비동기로 삭제가안됨
}
// 좋아요 버튼 상태 업데이트
function updateLikeButtonStyle(status) {
    const likeButton = document.getElementById("likeButton");

    if (status === "onClick") {
        likeButton.classList.add("btn-danger");
        likeButton.style.backgroundColor = "red";
        likeButton.style.color = "white";  // 클릭 시 흰색 글자
    } else {
        likeButton.classList.remove("btn-danger");
        likeButton.style.backgroundColor="white";
        likeButton.style.color = "red";    // 아닐 때 빨간 글자
    }
}