webpackJsonp([11],{jvsI:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={data:function(){return{page:"0",line:[],site:[],save:{line:{},site:{},projectMsg:{},weather:{}},tableData:[],siteID:""}},methods:{changeLine:function(t){var e=this;console.log(t);this.save.line=t,api.getLinesite(t.id).then(function(t){console.log(t.data.list),e.site=t.data.list,e.save.site=t.data.list[0],e.siteID=t.data.list[0].id}),this.getMainpageinformactionSelectbyrid(this.save.site.id)},getSelectAll:function(){var t=this;api.getSelectAll().then(function(e){console.log(e.data.list),t.line=e.data.list,t.save.line=e.data.list[0]})},changeSite:function(t){console.log(t),this.save.site=t,this.siteID=t.id,this.getMainpageinformactionSelectbyrid(this.save.site.id)},getLinesite:function(t){var e=this;api.getLinesite(t).then(function(t){console.log("****"),console.log(t),console.log(t.data.list),e.site=t.data.list,e.save.site=t.data.list[0],e.siteID=t.data.list[0].id,e.getMainpageinformactionSelectbyrid(e.save.site.id)})},getMeasurementproject:function(t,e){var n=this;api.getMeasurementproject(t,e,10).then(function(t){console.log(t.data.list),n.tableData=t.data.list})},getMainpageinformactionSelectbyrid:function(t){var e=this;api.getMainpageinformactionSelectbyrid(t).then(function(t){console.log("*********************"),console.log(t.data.msg),e.save.projectMsg=t.data.msg,e.getWeather(e.save.projectMsg.weatherid)})}}},s={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"surveyArchives"},[n("el-row",{staticClass:"Header"},[n("el-col",{attrs:{span:24}},[n("div",{staticClass:"grid-content bg-purple-dark"},[n("div",{staticClass:"select-item"},[n("p",[t._v("路线：")]),t._v(" "),n("el-dropdown",{staticClass:"select_",on:{command:t.changeLine}},[n("span",{staticClass:"el-dropdown-link"},[t._v("\n                        "+t._s(t.save.line.routename)),n("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),t._v(" "),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},t._l(t.line,function(e){return n("el-dropdown-item",{key:e.id,attrs:{command:e}},[t._v(t._s(e.routename))])}),1)],1),t._v(" "),n("p",{staticStyle:{"margin-left":"20px"}},[t._v("站点：")]),t._v(" "),n("el-dropdown",{staticClass:"select_",on:{command:t.changeSite}},[n("span",{staticClass:"el-dropdown-link"},[t._v("\n                        "+t._s(t.save.site.sitename)),n("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),t._v(" "),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},t._l(t.site,function(e){return n("el-dropdown-item",{key:e.id,attrs:{command:e}},[t._v(t._s(e.sitename))])}),1)],1),t._v(" "),n("p",{staticStyle:{"margin-left":"20px"}},[t._v("项目名称：")]),t._v(" "),n("el-dropdown",{staticClass:"select_",on:{command:t.changeSite}},[n("span",{staticClass:"el-dropdown-link"},[t._v("\n                        "+t._s(t.save.site.sitename)),n("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),t._v(" "),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},t._l(t.site,function(e){return n("el-dropdown-item",{key:e.id,attrs:{command:e}},[t._v(t._s(e.sitename))])}),1)],1)],1)])])],1),t._v(" "),n("div",{staticClass:"tableCon"},[[n("el-table",{staticStyle:{width:"100%",border:"1px solid #eee","border-bottom":"none"},attrs:{data:t.tableData}},[n("el-table-column",{attrs:{label:"项目名称",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.name))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"检测时间",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.date))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"综合预警",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.date))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"提交人",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.date))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"联系电话",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.date))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{attrs:{size:"mini"},on:{click:function(n){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(n){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")])]}}])})],1)]],2)],1)},staticRenderFns:[]};var i=n("C7Lr")(a,s,!1,function(t){n("xZD5")},"data-v-01f1d1f2",null);e.default=i.exports},xZD5:function(t,e){}});
//# sourceMappingURL=11.eb8f79d91b449b49e602.js.map