webpackJsonp([8],{FZM4:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("gyMJ"),i={data:function(){return{save:{turnoutstandardid:"",mileage:"",gauge:""},tableData:[],editStatus:!1,edit:{turnouttypename:"",reamke:"",id:""},addStatus:!1,add:{turnouttypename:"",reamke:""}}},methods:{addOne:function(){this.addStatus=!0},addOk:function(){var t=this;console.log(this.edit),this.save.mileage?this.save.gauge?s.a.newGaugestandard({turnoutid:this.save.turnoutstandardid,mileage:this.save.mileage,gauge:this.save.gauge}).then(function(e){console.log(e),"ok"==e.data.status?(t.editStatus=!1,t.addStatus=!1,t.$message({type:"success",message:"新增成功!"}),t.getLists()):t.$message({type:"error",message:"新增失败!"})}):this.$message({type:"error",message:"轨距不正确!"}):this.$message({type:"error",message:"里程不正确!"})},handleEdit:function(t,e){console.log(t,e),this.editStatus=!0,this.edit.turnouttypename=e.turnouttypename,this.edit.reamke=e.reamke,this.edit.id=e.id},handleDelete:function(t,e){var a=this;console.log(t,e),this.$confirm("此操作将永久删除该项, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s.a.delRoadClassify(e.id).then(function(t){console.log(t),"ok"==t.data.status?(a.$message({type:"success",message:"删除成功!"}),a.getLists()):a.$message({type:"error",message:"删除失败!"})})}).catch(function(){a.$message({type:"info",message:"已取消删除"})})},editChange:function(){var t=this;this.editStatus=!1,this.addStatus=!1,s.a.editRoadClassify(this.edit.id,{turnouttypename:this.edit.turnouttypename,reamke:this.edit.reamke}).then(function(e){console.log(e),"ok"==e.data.status?(t.$message({type:"success",message:"修改成功!"}),t.getLists()):t.$message({type:"error",message:"修改失败!"})})},editClose:function(){this.editStatus=!1,this.addStatus=!1},getLists:function(){var t=this;s.a.getRoadClassify().then(function(e){console.log(e),t.tableData=[],e.data.result.forEach(function(e){t.tableData.push({id:e.id,reamke:e.reamke,turnouttypename:e.turnouttypename})})})},getGaugestandard:function(){s.a.getGaugestandard().then(function(t){console.log(t.data.result)})}},created:function(){console.log(this.$route.params),this.save.turnoutstandardid=this.$route.params.turnoutstandardid,this.getLists(),this.getGaugestandard()}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"typeMange"},[a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{staticClass:"grid-content bg-purple-dark typeHeader"},[a("el-button",{attrs:{type:"primary"},on:{click:t.addOne}},[t._v("新增")])],1)])],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{staticClass:"grid-content bg-purple-dark typeMain"},[a("p",{staticStyle:{"padding-bottom":"20px"}},[t._v("标题")]),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{label:"里程",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.turnouttypename))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"轨距",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.reamke))]),t._v(" "),a("span",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}]},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")])]}}])})],1)],1)])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.editStatus||t.addStatus,expression:"editStatus || addStatus"}],staticClass:"typeAlert-bg"}),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.editStatus||t.addStatus,expression:"editStatus || addStatus"}],staticClass:"typeAlert"},[a("el-row",[a("el-col",[a("p",{staticStyle:{"padding-bottom":"20px"}},[t._v("修改")])])],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("里程：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.mileage,callback:function(e){t.$set(t.save,"mileage",e)},expression:"save.mileage"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("轨距：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.gauge,callback:function(e){t.$set(t.save,"gauge",e)},expression:"save.gauge"}})],1)],1),t._v(" "),a("el-row",[a("el-col",{directives:[{name:"show",rawName:"v-show",value:t.editStatus,expression:"editStatus"}],staticClass:"edit-submit",attrs:{span:24}},[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:t.editChange}},[t._v("确定修改")]),t._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:t.editClose}},[t._v("放弃修改")])],1),t._v(" "),a("el-col",{directives:[{name:"show",rawName:"v-show",value:t.addStatus,expression:"addStatus"}],staticClass:"edit-submit",attrs:{span:24}},[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:t.addOk}},[t._v("确定")]),t._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:t.editClose}},[t._v("放弃")])],1)],1)],1)],1)},staticRenderFns:[]};var o=a("C7Lr")(i,n,!1,function(t){a("lkFK")},"data-v-1f9bb916",null);e.default=o.exports},lkFK:function(t,e){}});
//# sourceMappingURL=8.6e151fbdf83eb625cdd2.js.map