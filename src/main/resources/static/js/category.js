window.onload = function(){
    // 각 종 elements
    const card = document.querySelector(".container");
    const text = document.getElementById("text");
    const question = document.getElementById("question");
    const answer = document.getElementById("answer");

    // 카드 클릭 애니메이션
    var check = null;
    card.addEventListener("click", function () {
        if (check == null){
            card.classList.add("rotate")
            setTimeout(function () {
                if (text.innerText == question.innerText) {
                    text.innerText = answer.innerText;
                }
                else {
                    text.innerText = question.innerText;
                }
                check = null;
            }, 500);
            check = setTimeout(function () {
                card.classList.remove("rotate")
                check = null;
            }, 1000);
        }
    })

    // 현재 문제 및 전체문제 수 체크
    const nowele = document.getElementById("now");
    var now = 1;
    const total = parseInt(document.getElementById("total").innerText);

    // 이전 문제 진행 요청
    const prev = document.getElementById("prev");
    prev.addEventListener("click", function () {
        if (now == 1) {
            alert("첫 카드입니다.")
        }
        else {
            if (check == null) {
                now -= 1;
                nowele.innerText = now;
                card.classList.add("left2right");
                setTimeout(function () {
                    //텍스트 변환
                }, 1000);
                check = setTimeout(function () {
                    card.classList.remove("left2right")
                    check = null;
                }, 2000);
            }
        }
    });

    // 다음 문제 진행 요청
    const next = document.getElementById("next");
    next.addEventListener("click", function () {
        console.log(total);
        if (now == total) {
            alert("마지막 카드입니다.")
        }
        else {
            if (check == null) {
                now += 1;
                nowele.innerText = now;
                card.classList.add("right2left");
                setTimeout(function () {
                    //텍스트 변환
                }, 1000);
                check = setTimeout(function () {
                    card.classList.remove("right2left")
                    check = null;
                }, 2000);
            }
        }
    });
}