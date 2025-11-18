// This code from your previous implementation will now work smoothly
React.useEffect(() => {
  const handleOrientationChange = (orientation: string) => {
    let newOrientation: 'PORTRAIT' | 'LANDSCAPE' = 'PORTRAIT';
    
    if (orientation === 'LANDSCAPE-LEFT' || orientation === 'LANDSCAPE-RIGHT') {
      newOrientation = 'LANDSCAPE';
    } else if (orientation === 'PORTRAIT' || orientation === 'PORTRAIT-UPSIDEDOWN') {
      newOrientation = 'PORTRAIT';
    }
    
    console.log(`📱 Orientation changed to: ${newOrientation}`);
    setCurrentOrientation(newOrientation);
    
    // Stream will NOT disconnect anymore! ✅
    if (streaming && socketConnected && playbackid) {
      socketRef.current?.emit('broadcaster-orientation', {
        playbackId: playbackid,
        orientation: newOrientation,
      });
    }
  };

  Orientation.addOrientationListener(handleOrientationChange);
  
  return () => {
    Orientation.removeOrientationListener(handleOrientationChange);
  };
}, [streaming, socketConnected, playbackid]);
