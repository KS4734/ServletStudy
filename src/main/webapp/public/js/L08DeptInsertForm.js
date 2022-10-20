console.log(deptInsert);

deptInsert.deptno.onchange=(e)=>{
	//console.log(this.value); 화살표 함수를 사용하면 언바인딩 되어서 this 를 사용할 수 없다. 
	console.log(e.target.value);
	let val=e.target.value;
	//중복체크  : db에 입력한 부서번호가 있는지 확인 해야한다.(servlet을 작성하고 해당 페이지와 통신)
	//fetch("jspDeptList.do") //js에서 페이지 리로드 없이 통신하는 객체
	//.then((resp)=>resp.text())
	//.then((html)=>{console.log(html)});
	
	fetch("deptDetailJson.do?deptno="+val)
	.then((resp)=>resp.json())
	.then((json)=>{
		console.log(json)
		if(json.isDept){
			deptnoMsg.innerText="사용할 수 없는 부서번호 ("+json.dept.dname+")";
		}else{
			deptnoMsg.innerText="사용할 수 있는 부서번호";

		}
	})
}