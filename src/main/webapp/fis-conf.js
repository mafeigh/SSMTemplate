// Global start
fis.match('*.jsp',{
    isHtmlLike : true,
    optimizer: fis.plugin('html-minifier')
});

fis.match('*.{js,css}', {
  useHash: true
});

fis.match('*.js', {
  optimizer: fis.plugin('uglify-js')
});

fis.match('*.css', {
  optimizer: fis.plugin('clean-css')
});


// ֻ��html�ļ���Ч���ϲ�js��css
fis.match('::package', {
  postpackager: fis.plugin('loader', {
    allInOne: true
  })
});

// Global end

