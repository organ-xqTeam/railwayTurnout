webpackJsonp([11],{Yi2f:function(t,e){},qaef:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("4YfN"),i=a.n(s),n=a("gyMJ"),o=a("2bvH"),r={data:function(){return{status:"",buttonType:"info",turnoutstandardid:"",radio:"2",fuhao:{calculation:"选择符号",id:"-1"},fuhaoObj:{},tableData:[],editStatus:!1,save:{dcbh:"",turnoutstandardid:"",measurementitem:"",range1:"",ranges:"",isdetails:"0",managementactegory:"A",inspectionmethod:"",standard1:"",state:""},edit:{turnouttypename:"",reamke:"",id:""},addStatus:!1,add:{turnouttypename:"",reamke:""}}},methods:{seeList:function(t,e){console.log(t,e),this.$router.push({name:"gaugeMeasureStandard",params:{turnoutstandardid:e.id}})},chooseShoudong:function(){console.log("选择手动输入"),this.fuhao={calculation:"选择符号",id:"-1"}},chooseFuhao:function(t){console.log(t),this.fuhao=t},addOne:function(){this.status="新增",this.addStatus=!0,this.save={dcbh:"",turnoutstandardid:this.turnoutstandardid,measurementitem:"",range1:"",ranges:"",isdetails:"0",managementactegory:"A",inspectionmethod:"",standard1:"",state:""}},addOk:function(){var t=this;if(console.log(this.save),console.log(this.fuhao),"-1"!=this.fuhao.id?(this.save.range1=this.fuhao.id,this.save.state="1"):(this.save.range1="0",this.save.state="0"),this.save.dcbh)if(this.save.measurementitem)if(this.save.ranges){var e=this.save;n.a.addMeasurementstandard(e).then(function(e){t.editStatus=!1,t.addStatus=!1,console.log(e),200==e.data.code?(t.$message({type:"success",message:"新增成功!"}),t.getLists(t.turnoutstandardid,"0")):t.$message({type:"error",message:"新增失败!"})})}else this.$message({type:"error",message:"输入不正确（数据）!"});else this.$message({type:"error",message:"输入不正确（标准名称）!"});else this.$message({type:"error",message:"输入不正确（道岔编号）!"})},handleEdit:function(t,e){this.status="修改",console.log(t,e),this.editStatus=!0,this.save=e},handleDelete:function(t,e){var a=this;console.log(t,e),this.status="删除",this.$confirm("此操作将永久删除该项, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){n.a.deleteMeasurementstandard("?id="+e.id).then(function(t){console.log(t),"1"==t.data?(a.$message({type:"success",message:"删除成功!"}),a.getLists(a.turnoutstandardid,"0")):a.$message({type:"error",message:"删除失败!"})})}).catch(function(){a.$message({type:"info",message:"已取消删除"})})},editChange:function(){var t=this;this.editStatus=!1,this.addStatus=!1,n.a.editRoadClassify(this.edit.id,{turnouttypename:this.edit.turnouttypename,reamke:this.edit.reamke}).then(function(e){console.log(e),"ok"==e.data.status?(t.$message({type:"success",message:"修改成功!"}),t.getLists()):t.$message({type:"error",message:"修改失败!"})})},editClose:function(){this.editStatus=!1,this.addStatus=!1},getLists:function(t,e){var a=this;n.a.getMeasurementstandard(t,e).then(function(t){console.log(t.data.result.list),a.tableData=t.data.result.list})}},computed:i()({},Object(o.b)({stateTurnoutstandardid:function(t){return t.measureStandardTemplate.turnoutstandardid},test:function(t){return t.test}})),created:function(){var t=this;console.log(this.$route.params),this.$route.params.turnoutstandardid&&(this.$store.state.measureStandardTemplate.turnoutstandardid=this.$route.params.turnoutstandardid),console.log(this.stateTurnoutstandardid),this.getLists(this.stateTurnoutstandardid,"0"),n.a.getCalculationstandard().then(function(e){var a=JSON.parse(e.data.result);console.log(a),t.fuhaoObj=a})}},l={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"typeMange"},[a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{staticClass:"grid-content bg-purple-dark typeHeader"},[a("el-button",{attrs:{type:"primary"},on:{click:t.addOne}},[t._v("新增")])],1)])],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{staticClass:"grid-content bg-purple-dark typeMain"},[a("div",{staticClass:"mb",staticStyle:{"padding-bottom":"20px"}},[a("el-breadcrumb",{attrs:{separator:"/"}},[a("el-breadcrumb-item",{attrs:{to:"/measureStandard"}},[t._v("测量标准")]),t._v(" "),a("el-breadcrumb-item",[t._v("测量标准模板")])],1)],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{label:"道岔编号",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.dcbh))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"标准名称",width:"580"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.measurementitem))]),t._v(" "),a("span",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}]},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")]),t._v(" "),1==e.row.isdetails?a("el-button",{attrs:{size:"mini",type:""},on:{click:function(a){return t.seeList(e.$index,e.row)}}},[t._v("查看")]):t._e()]}}])})],1)],1)])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.editStatus||t.addStatus,expression:"editStatus || addStatus"}],staticClass:"typeAlert-bg"}),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.editStatus||t.addStatus,expression:"editStatus || addStatus"}],staticClass:"typeAlert"},[a("el-row",[a("el-col",[a("p",{staticStyle:{"padding-bottom":"20px"}},[t._v(t._s(t.status))])])],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("道岔编号：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.dcbh,callback:function(e){t.$set(t.save,"dcbh",e)},expression:"save.dcbh"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("标准名称：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.measurementitem,callback:function(e){t.$set(t.save,"measurementitem",e)},expression:"save.measurementitem"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("检查方法和数量：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.inspectionmethod,callback:function(e){t.$set(t.save,"inspectionmethod",e)},expression:"save.inspectionmethod"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("选择符号：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("el-dropdown",{attrs:{trigger:"click"},on:{command:t.chooseFuhao}},[a("span",{staticClass:"el-dropdown-link",staticStyle:{cursor:"pointer"}},[t._v("\n            "+t._s(t.fuhao.calculation)),a("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},t._l(t.fuhaoObj,function(e){return a("el-dropdown-item",{key:e.id,attrs:{command:e}},[t._v(t._s(e.calculation))])}),1)],1),t._v(" "),a("el-button",{directives:[{name:"show",rawName:"v-show",value:-1!=t.fuhao.id,expression:"fuhao.id != -1"}],staticStyle:{"margin-left":"10px"},attrs:{type:"info",plain:"",size:"mini"},on:{click:t.chooseShoudong}},[t._v("选择手动输入")]),t._v(" "),a("el-button",{directives:[{name:"show",rawName:"v-show",value:-1==t.fuhao.id,expression:"fuhao.id == -1"}],staticStyle:{"margin-left":"10px"},attrs:{type:"primary",size:"mini"}},[t._v("当前为手动输入")])],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("数据：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:18}},[a("p",{directives:[{name:"show",rawName:"v-show",value:-1!=t.fuhao.id,expression:"fuhao.id != -1"}],staticStyle:{padding:"0 4px",border:"1px solid #DCDFE6","border-radius":"2px","margin-right":"5px"}},[t._v(t._s(t.fuhao.calculation))]),t._v(" "),a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.ranges,callback:function(e){t.$set(t.save,"ranges",e)},expression:"save.ranges"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:6}},[a("p",[t._v("检查意见：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:18}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.save.standard1,callback:function(e){t.$set(t.save,"standard1",e)},expression:"save.standard1"}})],1)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:8}},[a("p",[t._v("管理类别：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:16}},[[a("el-radio",{attrs:{label:"A"},model:{value:t.save.managementactegory,callback:function(e){t.$set(t.save,"managementactegory",e)},expression:"save.managementactegory"}},[t._v("A")]),t._v(" "),a("el-radio",{attrs:{label:"B"},model:{value:t.save.managementactegory,callback:function(e){t.$set(t.save,"managementactegory",e)},expression:"save.managementactegory"}},[t._v("B")]),t._v(" "),a("el-radio",{attrs:{label:"C"},model:{value:t.save.managementactegory,callback:function(e){t.$set(t.save,"managementactegory",e)},expression:"save.managementactegory"}},[t._v("C")])]],2)],1),t._v(" "),a("el-row",{staticClass:"edit-item"},[a("el-col",{attrs:{span:8}},[a("p",[t._v("是否有下一级：")])]),t._v(" "),a("el-col",{staticStyle:{display:"flex","align-items":"center"},attrs:{span:16}},[[a("el-radio",{attrs:{label:"1"},model:{value:t.save.isdetails,callback:function(e){t.$set(t.save,"isdetails",e)},expression:"save.isdetails"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"0"},model:{value:t.save.isdetails,callback:function(e){t.$set(t.save,"isdetails",e)},expression:"save.isdetails"}},[t._v("否")])]],2)],1),t._v(" "),a("el-row",[a("el-col",{directives:[{name:"show",rawName:"v-show",value:t.editStatus,expression:"editStatus"}],staticClass:"edit-submit",attrs:{span:24}},[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:t.editChange}},[t._v("确定修改")]),t._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:t.editClose}},[t._v("放弃修改")])],1),t._v(" "),a("el-col",{directives:[{name:"show",rawName:"v-show",value:t.addStatus,expression:"addStatus"}],staticClass:"edit-submit",attrs:{span:24}},[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:t.addOk}},[t._v("确定")]),t._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:t.editClose}},[t._v("放弃")])],1)],1)],1)],1)},staticRenderFns:[]};var d=a("C7Lr")(r,l,!1,function(t){a("Yi2f")},"data-v-3e7325d0",null);e.default=d.exports}});
//# sourceMappingURL=11.13ccbfbeb5081368d912.js.map