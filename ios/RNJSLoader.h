//
//  RNJSLoader.h
//  split_jsbundle
//
//  Created by ZhuXueliang on 2020/3/29.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <React/RCTBridge.h>

@interface RNJSLoader : NSObject

@property(nonatomic, strong, readonly)RCTBridge *bridge;
@property(nonatomic, strong, readonly)NSDictionary<NSString *, NSString *> *existingModues;

+ (instancetype)sharedInstance;
- (void)setupBridge:(RCTBridge *)bridge;
- (BOOL)loadJSModule:(NSString *)name path:(NSURL *)url;


@end
