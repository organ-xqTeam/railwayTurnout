webpackJsonp([11],{Iurx:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=n("gyMJ"),a={data:function(){return{line:[],save:{}}},methods:{getSelectAll:function(){var e=this;s.a.getSelectAll().then(function(t){console.log(t.data.list),e.line=t.data.list})},myDelete:function(e,t){var n=this;this.$confirm("此操作将永久删除该线路, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s.a.delTrainrouteinsert(t.id).then(function(e){console.log(e),"success"==e.data.stats?(n.getSelectAll(),n.$message({type:"success",message:"删除成功!"})):n.$message({type:"info",message:"删除失败"})})}).catch(function(){n.$message({type:"info",message:"已取消删除"})})},myEdit:function(e,t){var n=this;console.log(t),this.save=t,this.$prompt("请输入线路名称","修改站点",{confirmButtonText:"确定",cancelButtonText:"取消",inputValue:t.routename}).then(function(e){var a=e.value;s.a.changeLine({id:t.id,routename:a}).then(function(e){200==e.status?(n.getSelectAll(),n.$message({type:"success",message:"修改成功: "+a})):n.$message({type:"info",message:"修改失败"})})}).catch(function(){n.$message({type:"info",message:"取消输入"})})},newLine:function(){var e=this;this.$prompt("请输入线路名称","新增线路",{confirmButtonText:"确定",cancelButtonText:"取消"}).then(function(t){var n=t.value;s.a.newTrainrouteinsert({routename:n}).then(function(t){"success"==t.data.stats&&(e.getSelectAll(),e.$message({type:"success",message:"新增成功: "+n}))})}).catch(function(){e.$message({type:"info",message:"取消输入"})})}},created:function(){this.getSelectAll()}},i={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"line"},[n("el-row",[n("el-button",{attrs:{type:"primary"},on:{click:e.newLine}},[e._v("+ 添加线路")])],1),e._v(" "),n("div",{staticClass:"box"},[n("p",{staticClass:"title"},[e._v("线路")]),e._v(" "),n("div",{staticClass:"tableCon"},[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.line}},[n("el-table-column",{attrs:{prop:"routename",label:"线路名称",width:"380"}}),e._v(" "),n("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(n){return e.myEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(n){return e.myDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)])],1)},staticRenderFns:[]};var c=n("C7Lr")(a,i,!1,function(e){n("VUC2")},"data-v-0ac23100",null);t.default=c.exports},VUC2:function(e,t){}});
//# sourceMappingURL=11.f57f72ebde307097c4a5.js.map