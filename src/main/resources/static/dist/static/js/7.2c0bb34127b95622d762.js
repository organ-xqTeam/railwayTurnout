webpackJsonp([7],{"2kYh":function(e,t){},VPFL:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("gyMJ"),i={data:function(){return{save:{id:"",status:!1,identifiernum:"1",msg:"",msg1:"",num:""},currentPage:1,pagesize:10,count:0,tableData:[],page:0}},methods:{addShow:function(){this.save={status:"add",identifiernum:"1",msg:"",msg1:"",num:""}},close:function(){this.save.status=!1},sub:function(){switch(this.save.status){case"add":this.newQualitystandard();break;case"edit":this.changeQualitystandard()}this.save.status=!1,this.geTqualitystandard(),console.log(this.save)},geTqualitystandard:function(){var e=this;console.log("获取列表"),a.a.geTqualitystandard().then(function(t){console.log(t.data.result),e.tableData=t.data.result})},newQualitystandard:function(){var e=this;a.a.newQualitystandard({identifiernum:this.save.identifiernum,msg:this.save.msg,msg1:this.save.msg1,num:this.save.num}).then(function(t){console.log(t),"ok"==t.data.status?(e.geTqualitystandard(),e.$message({type:"success",message:"新增成功!"})):e.$message({message:"新增失败！",type:"warning"})})},changeQualitystandard:function(){var e=this;a.a.changeQualitystandard(this.save.id,{identifiernum:this.save.identifiernum,msg:this.save.msg,msg1:this.save.msg1,num:this.save.num}).then(function(t){console.log(t),"ok"==t.data.status?(e.$message({type:"success",message:"修改成功!"}),e.geTqualitystandard()):e.$message({message:"修改失败！",type:"warning"})})},handleEdit:function(e,t){console.log(t),this.save={id:t.id,status:"edit",identifiernum:t.identifiernum,msg:t.msg,msg1:t.msg1,num:t.num}},handleDelete:function(e,t){var s=this;console.log(t.id),this.$confirm("此操作将永久删除该文件, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.a.delQualitystandard(t.id).then(function(e){console.log(e),"ok"==e.data.status?(s.$message({type:"success",message:"删除成功!"}),s.geTqualitystandard()):s.$message({message:"删除失败！",type:"warning"})})}).catch(function(){s.$message({type:"info",message:"已取消删除"})})}},created:function(){this.geTqualitystandard()}},n={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"wrap"},[s("el-row",[s("el-button",{attrs:{type:"primary"},on:{click:e.addShow}},[e._v("新增")])],1),e._v(" "),s("div",{staticClass:"tableCon"},[s("el-row",[s("el-col",{attrs:{span:24}},[s("div",{staticClass:"grid-content bg-purple-dark typeMain"},[s("p",{staticStyle:{"padding-bottom":"20px"}},[e._v("质量分析标准")]),e._v(" "),s("el-table",{staticStyle:{width:"100%",border:"1px solid #eee","border-bottom":"none"},attrs:{data:e.tableData}},[s("el-table-column",{attrs:{prop:"num",label:"编号"}}),e._v(" "),s("el-table-column",{attrs:{prop:"msg1",label:"标准"}}),e._v(" "),s("el-table-column",{attrs:{prop:"msg",label:"说明"}}),e._v(" "),s("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(s){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),s("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(s){return e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)])],1)],1),e._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:e.save.status,expression:"save.status"}],staticClass:"alert_bg"}),e._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:e.save.status,expression:"save.status"}],staticClass:"alert"},[s("div",{staticClass:"title",staticStyle:{"padding-bottom":"30px"}},[e._v("新增")]),e._v(" "),s("div",{staticClass:"item"},[s("p",[e._v("编号：")]),e._v(" "),s("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.save.num,callback:function(t){e.$set(e.save,"num",t)},expression:"save.num"}})],1),e._v(" "),s("div",{staticClass:"item"},[s("p",[e._v("标准：")]),e._v(" "),s("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.save.msg1,callback:function(t){e.$set(e.save,"msg1",t)},expression:"save.msg1"}})],1),e._v(" "),s("div",{staticClass:"item"},[s("p",[e._v("说明：")]),e._v(" "),s("el-input",{attrs:{type:"textarea",rows:2,placeholder:"请输入内容"},model:{value:e.save.msg,callback:function(t){e.$set(e.save,"msg",t)},expression:"save.msg"}})],1),e._v(" "),s("div",{staticClass:"item"},[s("p",[e._v("分类：")]),e._v(" "),s("div",[[s("el-radio",{attrs:{label:"1"},model:{value:e.save.identifiernum,callback:function(t){e.$set(e.save,"identifiernum",t)},expression:"save.identifiernum"}},[e._v("分类1")]),e._v(" "),s("el-radio",{attrs:{label:"2"},model:{value:e.save.identifiernum,callback:function(t){e.$set(e.save,"identifiernum",t)},expression:"save.identifiernum"}},[e._v("分类2")])]],2)]),e._v(" "),s("el-row",{staticClass:"sub_b"},[s("el-button",{attrs:{size:"mini"},on:{click:e.close}},[e._v("取消")]),e._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.sub}},[e._v("确认")])],1)],1)],1)},staticRenderFns:[]};var l=s("C7Lr")(i,n,!1,function(e){s("2kYh")},"data-v-456d3ede",null);t.default=l.exports}});
//# sourceMappingURL=7.2c0bb34127b95622d762.js.map