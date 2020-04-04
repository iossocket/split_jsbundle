//
//  RNJSLoader.m
//  split_jsbundle
//
//  Created by ZhuXueliang on 2020/3/29.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNJSLoader.h"
#import <React/RCTBridge+Private.h>

@interface RCTBridge (RnLoadJS)
- (void)executeSourceCode:(NSData *)sourceCode sync:(BOOL)sync;
@end

@interface RNJSLoader ()
@property(nonatomic, strong) RCTBridge *bridge;
@property(nonatomic, strong) NSDictionary<NSString *, NSString *> *existingModues;
@property(nonatomic, strong) NSMutableDictionary<NSString *, NSURL *> *jsModules;
@end

@implementation RNJSLoader

+ (instancetype)sharedInstance {
  static RNJSLoader *sharedInstance = nil;
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
      sharedInstance = [[self alloc] init];
  });
  return sharedInstance;
}

- (instancetype)init {
  if (self = [super init]) {
    self.jsModules = [NSMutableDictionary dictionary];
    self.existingModues = @{@"split_jsbundle": @"main_app", @"rewards": @"sub_app"};
  }
  return self;
}

- (void)setupBridge:(RCTBridge *)bridge {
  self.bridge = bridge;
}

- (BOOL)loadJSModule:(NSString *)name path:(NSURL *)url {
  if (!self.bridge) {
    return NO;
  }
  NSURL *cachedUrl = [self.jsModules objectForKey:name];
  if (cachedUrl) {
    return YES;
  }
  NSError *error = nil;
  NSData *sourceData = [NSData dataWithContentsOfURL:url options:NSDataReadingMappedIfSafe error:&error];
  if (error) {
    return NO;
  }
  
  [self.bridge.batchedBridge executeSourceCode:sourceData sync:NO];
  [self.jsModules setValue:url forKey:name];
  return YES;
}

@end
