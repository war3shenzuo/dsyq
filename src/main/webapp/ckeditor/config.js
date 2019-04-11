/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.filebrowserUploadUrl=basePath+"/common/uploadNewsImg2.do";
	
/*	config.toolbar = 'MyToolbar';//把默认工具栏改为‘MyToolbar’     
	    
    config.toolbar_MyToolbar =     
    [          
        ['Image','Link','Unlink']             
    ]; */    
    config.toolbar = 'Full'; 

    config.toolbar_Full = 
    [ 
    ['Source','-','Save','NewPage','Preview','-','Templates'], 
    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'], 
    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], 
    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'], 
    ['BidiLtr', 'BidiRtl'], 
    '/', 
    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'], 
    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
    ['Link','Unlink','Anchor'], 
    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'], 
    '/', 
    ['Styles','Format','Font','FontSize'], 
    ['TextColor','BGColor'], 
    ['Maximize', 'ShowBlocks','-','About'] 
    ]; 

 
};
