$(function(){
    //
    $('.timeRange').click(function(){
        $('#timeRange_div').remove();
         
        var hourOpts = '';
        for (i=0;i<24;i++) hourOpts += '<option>'+i+'</option>';
//        var minuteOpts = '<option>00</option><option>10</option><option>20</option><option>30</option><option>40</option><option>50</option>';
        var minuteOpts = '<option>00</option><option>30</option>';
         
        var html = $('<div id="timeRange_div"><select id="timeRange_a">'+hourOpts+
            '</select> : <input type="text" value="00" placeholder="" id="timeRange_b" readonly="readonly" style="width:37px; height:20px"> : <input type="text" value="00" placeholder="" id="timeRange_c" readonly="readonly" style="width:37px; height:20px"><input type="button" value="确定" id="timeRange_btn" /></div>')
            .css({
                "position": "absolute",
                "z-index": "999",
                "padding": "5px",
                "border": "1px solid #AAA",
                "background-color": "#FFF",
                "box-shadow": "1px 1px 3px rgba(0,0,0,.4)"
            })
            .click(function(){return false});
        // 如果文本框有值
        var v = $(this).val();
        if (v) {
            v = v.split(/:/);
            html.find('#timeRange_a').val(v[0]);
            html.find('#timeRange_b').val(v[1]);
            html.find('#timeRange_c').val(v[1]);
        }
        // 点击确定的时候
        var pObj = $(this);
        html.find('#timeRange_btn').click(function(){
            var str = html.find('#timeRange_a').val()+':'
                +html.find('#timeRange_b').val()+':'
            +html.find('#timeRange_c').val();
            pObj.val(str);
            $('#timeRange_div').remove();
        });
         
        $(this).after(html);
        return false;
    });
    //
    $(document).click(function(){
        $('#timeRange_div').remove();
    });
    //
});