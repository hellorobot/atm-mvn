<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="demo">
<select v-model="selected">  
  <option v-for="option in options" v-bind:value="option.value">  
    {{ option.text }}  
  </option>  
</select>  
<span>Selected: {{ selected }}</span>  
</div>
<jsp:include page="/WEB-INF/pages/common/footer.jsp"></jsp:include>
<script type="text/javascript">
new Vue({  
	  el: '#demo',  
	  data: {  
	    selected: 'A',  
	    options: [  
	      { text: 'One', value: 'A' },  
	      { text: 'Two', value: 'B' },  
	      { text: 'Three', value: 'C' }  
	    ]  
	  }  
	})  
</script>

