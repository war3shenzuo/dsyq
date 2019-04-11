/**
 * Created by Alan on 2016/10/21.
 */
$(document).ready(function(){

    $('#roomModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        var modal = $(this)
        $('#formal').val(recipient)
    })

    var floortree = $("#floor-tree").jstree({
        "core": {
            "animation": true,
            "multiple": false,
            "check_callback": true,
            "themes": { "stripes": false },
            'data': {
                'url': basePath+"/floor/getFloorList.do?status=1",
                "dataType": "json",
                'data': function (node) {
                    return { 'id': node.id };
                }
            }
        }

    }).on("changed.jstree", function (event, data) {

        if (data.selected.length > 0) {

            var id=data.selected[0];
            if($('#floor-tree').jstree('is_closed',id))
            {
                $('#floor-tree').jstree('open_node',id);
            }else
            {
                $('#floor-tree').jstree('close_node',id);
            }

            var p1=$('#floor-tree').jstree().get_parent(id);

            var p2=$('#floor-tree').jstree().get_parent(p1);

            var p3=$('#floor-tree').jstree().get_parent(p2);

            if(p1=='#')
            {

                if($("#formal").val()=='formal'){
                    $('#floorNum').val($('#floor-tree').jstree().get_text(id));
                    $('#floorLayer').val('');
                    $('#floorPartition').val('');
                    $('#floorRoom').val('');
                }else{
                    $('#floorNumSpare').val($('#floor-tree').jstree().get_text(id));
                    $('#floorLayerSpare').val('');
                    $('#floorPartitionSpare').val('');
                    $('#floorRoomSpare').val('');
                }

            }

            if(p2=='#')
            {

                if($("#formal").val()=='formal'){
                    $('#floorNum').val($('#floor-tree').jstree().get_text(p1));
                    $('#floorLayer').val($('#floor-tree').jstree().get_text(id));
                    $('#floorPartition').val('');
                    $('#floorRoom').val('');
                    $('#rooms-select').empty();
                }else{
                    $('#floorNumSpare').val($('#floor-tree').jstree().get_text(p1));
                    $('#floorLayerSpare').val($('#floor-tree').jstree().get_text(id));
                    $('#floorPartitionSpare').val('');
                    $('#floorRoomSpare').val('');
                    $('#rooms-select').empty();
                }

            }

            if(p3=='#')
            {

                if($("#formal").val()=='formal'){
                    $('#floorNum').val($('#floor-tree').jstree().get_text(p2));
                    $('#floorLayer').val($('#floor-tree').jstree().get_text(p1));
                    $('#floorPartition').val($('#floor-tree').jstree().get_text(id));
                    $('#floorRoom').val('');
                    $('#rooms-select').empty();
                }else{
                    $('#floorNumSpare').val($('#floor-tree').jstree().get_text(p2));
                    $('#floorLayerSpare').val($('#floor-tree').jstree().get_text(p1));
                    $('#floorPartitionSpare').val($('#floor-tree').jstree().get_text(id));
                    $('#floorRoomSpare').val('');
                    $('#rooms-select').empty();
                }

                $('#rooms-select').height($('#floor-tree').height());

                loadRooms(id);

            }


        }
    }).jstree();


    $('#rooms-select').on('click',function(){

        var s=$('#rooms-select option:selected').val();
        var areaId = $('#floor-tree').jstree().get_selected()[0];//获取选中的树节点id
        var storeyId = $('#floor-tree').jstree().get_parent(areaId);
        var floorId = $('#floor-tree').jstree().get_parent(storeyId);
        if(s!='')
        {
            $('#ref-room-id').val(s);

            if($("#formal").val()=='formal'){
                $('#floorNum').val($('#floor-tree').jstree().get_text(floorId));
                $('#floorLayer').val($('#floor-tree').jstree().get_text(storeyId));
                $('#floorPartition').val($('#floor-tree').jstree().get_text(areaId));
            }else{
                $('#floorNumSpare').val($('#floor-tree').jstree().get_text(floorId));
                $('#floorLayerSpare').val($('#floor-tree').jstree().get_text(storeyId));
                $('#floorPartitionSpare').val($('#floor-tree').jstree().get_text(areaId));
            }

            loadRoomInfo();
        }

    })

})



function loadRooms(id)
{
    $.post(basePath+'/floor/getRooms.do',{areaId:id,activated :'1'},function(data){


        if(data.status===10001)
        {
            $('#rooms-select').empty();

            for(var i=0;i<data.data.length;i++)
            {
                var oOption=document.createElement('option');

                oOption.value=data.data[i].id;

                oOption.text=data.data[i].roomNum;

                $('#rooms-select').append(oOption);

            }
        }

    })

}


function loadRoomInfo() {
    var rid=$('#ref-room-id').val();
    if(rid=='')
        return;

    $.post(basePath+'/floor/getRoom.do',{roomId:rid},function(data){


        if(data.status===10001)
        {
            var r=data.data;

            if($("#formal").val()=='formal'){
                $('#floorRoom').val(r.roomNum);
            }else{
                $('#floorRoomSpare').val(r.roomNum);
            }

        }
        else
        {
            myNoty('取房间信息错误。',10002);
        }


    })

}

//刷新列表数据
function refreshCompanyIntentionList()
{
    window.parent.refreshIframe(basePath+"/etopCompanyIntention/etopCompanyIntentionList.do");
}

