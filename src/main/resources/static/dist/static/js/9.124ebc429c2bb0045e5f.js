webpackJsonp([9],{CDz3:function(e,t){},I644:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=s("gyMJ"),n={data:function(){return{line:[],site:[],save:{title:"新增",line:{},sitename:"",status:""},addShow:!1}},methods:{myDelete:function(e,t){var s=this;this.$confirm("此操作将永久删除该线路, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i.a.deleteLinesite(t.id).then(function(e){console.log(e),"1"==e.data?(s.getLinesite(s.save.line.id),s.$message({type:"success",message:"删除成功!"})):s.$message({type:"info",message:"删除失败"})})}).catch(function(){s.$message({type:"info",message:"已取消删除"})})},myEdit:function(e,t){this.addShow=!0,this.save.title="修改",this.save.status="edit",this.save.sitename=t.sitename,console.log(t)},addSite:function(e){this.save.title="新增",this.save.status="add",this.addShow=!0,this.save.sitename=""},sub:function(){switch(this.save.status){case"add":this.newLinesite()}},newLinesite:function(){var e=this;i.a.newLinesite({routeid:this.save.line.id,sitename:this.save.sitename}).then(function(t){e.addShow=!1,"success"==t.data.stats?(e.getLinesite(e.save.line.id),e.$message({type:"success",message:"新增成功!"})):(e.addShow=!1,e.$message({type:"info",message:"新增失败"}))})},changeLine:function(e){console.log(e),this.save.line=e,this.getLinesite(e.id)},getSelectAll:function(){var e=this;i.a.getSelectAll().then(function(t){console.log(t),e.line=t.data.list,e.save.line=t.data.list[0]})},getLinesite:function(e){var t=this;i.a.getLinesite(e).then(function(e){console.log(e.data.list),t.site=e.data.list})},close:function(){this.addShow=!1}},created:function(){var e=this;this.getSelectAll(),setTimeout(function(){e.getLinesite(e.save.line.id)},200)}},a={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"site"},[s("el-row",{staticClass:"top_li"},[s("p",[e._v("当前线路：")]),e._v(" "),s("el-dropdown",{staticClass:"select_",on:{command:e.changeLine}},[s("span",{staticClass:"el-dropdown-link"},[e._v("\n                "+e._s(e.save.line.routename)),s("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),e._v(" "),s("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.line,function(t){return s("el-dropdown-item",{key:t.id,attrs:{command:t}},[e._v(e._s(t.routename))])}),1)],1),e._v(" "),s("el-button",{staticStyle:{"margin-left":"20px"},attrs:{type:"primary"},on:{click:e.addSite}},[e._v("+ 添加站点")])],1),e._v(" "),s("div",{staticClass:"box"},[s("p",{staticClass:"title"},[e._v("站点")]),e._v(" "),s("div",{staticClass:"tableCon"},[s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.site}},[s("el-table-column",{attrs:{prop:"sitename",label:"线路名称",width:"180"}}),e._v(" "),s("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(s){return e.myEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),s("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(s){return e.myDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)]),e._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:e.addShow,expression:"addShow"}],staticClass:"alert_bg"}),e._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:e.addShow,expression:"addShow"}],staticClass:"alert"},[s("div",{staticClass:"con"},[s("p",{staticClass:"title"},[e._v(e._s(e.save.title))]),e._v(" "),s("div",{staticClass:"c"},[s("p",[e._v("当前线路：")]),e._v(" "),s("el-dropdown",{staticClass:"select_",on:{command:e.changeLine}},[s("span",{staticClass:"el-dropdown-link"},[e._v("\n                        "+e._s(e.save.line.routename)),s("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),e._v(" "),s("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.line,function(t){return s("el-dropdown-item",{key:t.id,attrs:{command:t}},[e._v(e._s(t.routename))])}),1)],1)],1),e._v(" "),s("div",{staticClass:"s"},[s("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.save.sitename,callback:function(t){e.$set(e.save,"sitename",t)},expression:"save.sitename"}})],1),e._v(" "),s("el-row",{staticClass:"d"},[s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.sub}},[e._v("确定")]),e._v(" "),s("el-button",{attrs:{size:"mini"},on:{click:e.close}},[e._v("取消")])],1)],1)])],1)},staticRenderFns:[]};var o=s("C7Lr")(n,a,!1,function(e){s("CDz3")},"data-v-1abf1be0",null);t.default=o.exports}});
//# sourceMappingURL=9.124ebc429c2bb0045e5f.js.map