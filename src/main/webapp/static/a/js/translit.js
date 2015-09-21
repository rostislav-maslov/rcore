function translitInputToInput(selectorInputFrom, selectorInputTo){
    $(selectorInputTo).val(translit($(selectorInputFrom).val(), true));
}

function translit(text, toLowerCase){
    var transl=new Array();
    //Engl
    transl['Q']='Q';	transl['q']='q';
    transl['W']='W';	transl['w']='w';
    transl['E']='E';	transl['e']='e';
    transl['R']='R';	transl['r']='r';
    transl['T']='T';	transl['t']='t';
    transl['Y']='Y';	transl['y']='y';
    transl['U']='U';	transl['u']='u';
    transl['I']='I';	transl['i']='i';
    transl['O']='O';	transl['o']='o';
    transl['P']='P';	transl['p']='p';
    transl['A']='A';	transl['a']='a';
    transl['S']='S';	transl['s']='s';
    transl['D']='D';	transl['d']='d';
    transl['F']='F';	transl['f']='f';
    transl['G']='G';	transl['g']='g';
    transl['H']='H';	transl['h']='h';
    transl['J']='J';	transl['j']='j';
    transl['K']='K';	transl['k']='k';
    transl['L']='L';	transl['l']='l';
    transl['Z']='Z';	transl['z']='z';
    transl['X']='X';	transl['x']='x';
    transl['C']='C';	transl['c']='c';
    transl['V']='V';	transl['v']='v';
    transl['B']='B';	transl['b']='b';
    transl['N']='N';	transl['n']='n';
    transl['M']='M';	transl['m']='m';

    //digit
    transl['0']='0';
    transl['1']='1';
    transl['2']='2';
    transl['3']='3';
    transl['4']='4';
    transl['5']='5';
    transl['6']='6';
    transl['7']='7';
    transl['8']='8';
    transl['9']='9';

    //Рус
    transl['А']='A';	transl['а']='a';
    transl['Б']='B';	transl['б']='b';
    transl['В']='V';	transl['в']='v';
    transl['Г']='G';	transl['г']='g';
    transl['Д']='D';	transl['д']='d';
    transl['Е']='E';	transl['е']='e';
    transl['Ё']='Yo';	transl['ё']='yo';
    transl['Ж']='Zh';	transl['ж']='zh';
    transl['З']='Z';	transl['з']='z';
    transl['И']='I';	transl['и']='i';
    transl['Й']='J';	transl['й']='j';
    transl['К']='K';	transl['к']='k';
    transl['Л']='L';	transl['л']='l';
    transl['М']='M';	transl['м']='m';
    transl['Н']='N';	transl['н']='n';
    transl['О']='O';	transl['о']='o';
    transl['П']='P';	transl['п']='p';
    transl['Р']='R';	transl['р']='r';
    transl['С']='S';	transl['с']='s';
    transl['Т']='T';	transl['т']='t';
    transl['У']='U';	transl['у']='u';
    transl['Ф']='F';	transl['ф']='f';
    transl['Х']='X';	transl['х']='x';
    transl['Ц']='C';	transl['ц']='c';
    transl['Ч']='Ch';	transl['ч']='ch';
    transl['Ш']='Sh';	transl['ш']='sh';
    transl['Щ']='Shh';	transl['щ']='shh';
    transl['Ъ']='"';	transl['ъ']='"';
    transl['Ы']='Y\'';	transl['ы']='y\'';
    transl['Ь']='\'';	transl['ь']='\'';
    transl['Э']='E\'';	transl['э']='e\'';
    transl['Ю']='Yu';	transl['ю']='yu';
    transl['Я']='Ya';	transl['я']='ya';

    //без ковычек
    var typebox=true
    if(typebox==true){
        transl['Ъ']='';		transl['ъ']='';
        transl['Ы']='Y';	transl['ы']='y';
        transl['Ь']='';		transl['ь']='';
        transl['Э']='E';	transl['э']='e';
    }

    text=text.split(''); var result='';
    for(var i=0;i<text.length;i++){
        if(transl[text[i]]!=undefined){
            result+=transl[text[i]];
        }else{
            if(text[i]==' '){ result+='-'; }
        }
    }

    if(toLowerCase==true){
        result=result.toLowerCase();
    }
    return result;
}