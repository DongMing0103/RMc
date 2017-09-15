var positionFormatter={};
/*操作*/
positionFormatter.operateFormatter=function(value, row, index) {
    return [
        '<a class="crmPositionEdit" href="javascript:void(0);" onclick="position.editPosition('+row.id+',\''+row.name+'\')">编辑</a>&nbsp&nbsp&nbsp',
        '<a class="crmPositionDelete" href="javascript:void(0);" class="pl15" onclick="position.deletePosition('+row.id+')">删除</a>'
    ].join('');
}